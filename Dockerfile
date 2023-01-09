FROM openjdk:latest
COPY target/lab3-country.jar lab3-country.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "lab3-country.jar"]