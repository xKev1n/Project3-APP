FROM maven:3.8.3-jdk-11-slim AS build
COPY pom.xml /app/pom.xml
COPY src /app/src
WORKDIR /app
RUN mvn clean install -DskipTests && \
cp /app/target/BDS-APP-*.jar /app/BDS-APP.jar
ENTRYPOINT ["java", "-Dprism.order=sw", "-Djdk.gtk.version=2", "-jar", "/app/BDS-APP.jar"]

