# Use an official Java runtime as a parent image
#FROM openjdk:17-jdk-slim


# Build stage
FROM maven:3.9.8-eclipse-temurin-17 AS build
# Set working directory
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -U -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -e -B -DskipTests package

# Run stage
FROM eclipse-temurin:17-jre
# Set working directory
WORKDIR /app
# Copy the JAR file from the target folder
COPY --from=build /app/target/library-management-*.jar app.jar
#COPY target/library-management-1.0.0.4.jar app.jar
# Expose the application port
EXPOSE 8080
# Run the application
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]

