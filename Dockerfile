FROM amazoncorretto:17
RUN mkdir -p /home/app

COPY . /home/app

CMD ["java", "-jar", "/home/app/target/karantodo-1.0-SNAPSHOT.jar", "server", "/home/app/config.yml"]

#WORKDIR /app
#COPY ./target/karantodo-1.0-SNAPSHOT.jar /app
#EXPOSE 8080