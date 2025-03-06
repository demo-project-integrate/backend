# Use OpenJDK base image
FROM --platform=linux/amd64 openjdk:21

# Set working directory
WORKDIR /app

# Copy the built JAR file
COPY target/backend.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
