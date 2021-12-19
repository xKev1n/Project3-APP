FROM openjdk:15
ADD target/maven-docker-app.jar maven-docker-app.jar
ENTRYPOINT ["java", "-jar","maven-docker-app.jar"]
EXPOSE 8080