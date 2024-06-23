FROM amazoncorretto:17
WORKDIR /app
COPY ./target/karantodo-1.0-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "target/karantodo-1.0-SNAPSHOT.jar ", "server", "config.yml"]