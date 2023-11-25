FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-8-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:8-jdk-slim

EXPOSE 8089

COPY --from=build /target/registration-login-spring-boot-security-thymeleaf-mysql-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

CMD ["mvn", "spring-boot:run"]
