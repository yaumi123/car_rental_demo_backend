FROM openjdk:8

COPY target/car-rental-prototype-0.0.1-SNAPSHOT.jar /car-rental-prototype.jar

CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/car-rental-prototype.jar"]
