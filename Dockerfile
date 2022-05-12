FROM openjdk:16-alpine3.13 as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ["./mvnw", "dependency:go-offline"]
COPY src ./src

FROM base as test
RUN ["./mvnw", "test"]

FROM base as development
CMD ["./mvnw", "spring-boot:run"]

FROM base as build
RUN ["./mvnw", "package"]

FROM openjdk:11-jre-slim as production
COPY --from=build /app/target/todo-backend-*.jar /todo-backend.jar
CMD ["sh", "-c", "java -Dserver.port=$PORT $JAVA_OPTS -Dspring.profiles.active=prod -jar /todo-backend.jar"]
