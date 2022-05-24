package com.s0qva.todobackend.aspect;

import com.s0qva.todobackend.exception.model.IncorrectDataContainer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution(* com.s0qva.todobackend.controller.*.*(..))")
    private void allControllerMethods() {}

    @Pointcut("execution(* com.s0qva.todobackend.service.*.*(..))")
    private void allServiceMethods() {}

    @Pointcut("execution(* com.s0qva.todobackend.mapper.*.*.*(..))")
    private void allMapperMethods() {}

    @Pointcut("execution(* com.s0qva.todobackend.exception.handler.GlobalExceptionHandler.*(..))")
    private void exceptionHandlerHandleMethods() {}

    @Around("allControllerMethods()")
    public Object logAllControllerMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return log(proceedingJoinPoint);
    }

    @Around("allServiceMethods()")
    public Object logAllServiceMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return log(proceedingJoinPoint);
    }

    @Around("allMapperMethods()")
    public Object logAllMapperMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return log(proceedingJoinPoint);
    }

    @Around("exceptionHandlerHandleMethods()")
    public Object logExceptionHandlerHandleMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return log(proceedingJoinPoint);
    }

    private Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature methodSignature = proceedingJoinPoint.getSignature();
        String calledClass = methodSignature.getDeclaringTypeName();
        String calledMethodName = methodSignature.getName();

        log.info("{}: {} method is being called", calledClass, calledMethodName);
        Object methodResult = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());

        if (calledClass.equals("com.s0qva.todobackend.exception.handler.GlobalExceptionHandler")) {
            logException(methodResult);
        }

        log.info("{}: {} method has been finished", calledClass, calledMethodName);
        return methodResult;
    }

    private void logException(Object methodResult) {
        ResponseEntity<IncorrectDataContainer> responseEntity = (ResponseEntity<IncorrectDataContainer>) methodResult;
        log.error("HTTP status code: {}, errors: {}", responseEntity.getStatusCode(), responseEntity.getBody().getErrors());
    }
}
