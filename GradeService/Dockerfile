FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} grade-service.jar
ENTRYPOINT ["java", "-jar", "/grade-service.jar"]
EXPOSE 9001