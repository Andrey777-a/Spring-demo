# Stage 1: Build the application
#FROM maven:3.9.5-eclipse-temurin-21 AS builder
FROM maven:3.9.5-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY mvnw pom.xml lombok.config ./
COPY ./src ./src
#Copy all file in project
#COPY . .
RUN mvn clean install -DskipTests

# Stage 2: Create a minimal runtime image
#FROM openjdk:21
#small version
FROM maven:3.9.5-eclipse-temurin-21-alpine
WORKDIR /app
COPY --from=builder /app/target/spring-demo-1.0-SNAPSHOT.jar ./spring-demo-1.0-SNAPSHOT.jar
#EXPOSE 8080
#CMD ["java", "-jar", "spring-demo-1.0-SNAPSHOT.jar"]