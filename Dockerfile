# Use a base image with Java runtime environment
FROM openjdk:21-jdk-slim

# Set a working directory
WORKDIR /app

# Copy the JAR file from the target folder into the container
COPY target/examplan-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (if your app runs on port 8080, for example)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
