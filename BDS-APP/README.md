To build this application you need to run:

mvn clean install
java -jar .\target\BDS-APP-1.0.0.jar

Dockerization:

docker build -t docker_app .
docker run docker_app

Application to BPC-BDS
Third project


Firstly you need to log in with an employee account.
Authentization is made by BCrypt.
Application to create, edit, show info and detailed info about employees in database Library.
Application allows you to try SQL Injection Attacks on Dummy table.
