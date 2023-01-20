FROM openjdk:11.0.7-jdk-slim
EXPOSE 8081

COPY /target/*.jar ecviron-api.jar
ENTRYPOINT ["java","-jar","/ecviron-api.jar"]