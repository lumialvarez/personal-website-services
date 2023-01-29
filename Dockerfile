FROM openjdk:17.0.2-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9292
ENTRYPOINT ["java","-jar","/app.jar"]