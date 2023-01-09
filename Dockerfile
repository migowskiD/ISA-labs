FROM openjdk:latest
COPY target/lab3-continent.jar lab3-continent.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "lab3-continent.jar"]