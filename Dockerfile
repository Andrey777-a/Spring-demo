# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Stage 2: Create a minimal runtime image
FROM openjdk:21
WORKDIR /app
COPY --from=builder /app/target/spring-demo-1.0-SNAPSHOT.jar ./spring-demo-1.0-SNAPSHOT.jar
#EXPOSE 8080
#CMD ["java", "-jar", "spring-demo-1.0-SNAPSHOT.jar"]