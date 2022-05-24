package com.s0qva.todobackend.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class RegexUtil {

    public String findReceivedDate(HttpMessageNotReadableException exception) {
        Pattern compile = Pattern.compile("(\".{0,128}\")");
        Matcher matcher = compile.matcher(exception.getMessage().strip().toLowerCase());
        return matcher.find() ? matcher.group(0).replace("\"", "'") : "received date";
    }
}
