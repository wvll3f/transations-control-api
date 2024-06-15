FROM maven:3.8.8-sapmachine-21 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim

COPY --from=build /app/target/secutity-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080:8080

CMD ["java", "-jar", "app.jar"]

