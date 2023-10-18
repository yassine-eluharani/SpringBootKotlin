FROM openjdk:11.0.12-jdk-slim
VOLUME /tmp
COPY target/*.jar productAPI.jar
ENTRYPOINT ["java", "-jar", "/productAPI.jar"]
