FROM openjdk:11.0.7-jdk-slim
EXPOSE 8080

COPY /target/*.jar ecviron-processor.jar
ENTRYPOINT ["java","-jar","/ecviron-processor.jar"]