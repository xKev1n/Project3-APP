FROM openjdk:15
WORKDIR /target
COPY . /target
ENTRYPOINT [ "java","-Dprism.order=sw","-Djdk.gtk.version=2","-jar","maven-docker-app.jar" ]