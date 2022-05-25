echo off
cd D:\Projects\todo-backend
call mvnw.cmd flyway:clean flyway:migrate -DFLYWAY_URL=%FLYWAY_REMOTE_URL% -DFLYWAY_USER=%FLYWAY_REMOTE_USER% -DFLYWAY_PASSWORD=%FLYWAY_REMOTE_PASSWORD%
