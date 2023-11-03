FROM openjdk:17

COPY build/libs/*T.jar app.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod","app.jar"]