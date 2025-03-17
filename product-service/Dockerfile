# Use Eclipse Temurin JDK 21 as the base image
FROM eclipse-temurin:21-jdk
# Set the working directory inside the container
WORKDIR /app
# Copy the Maven build JAR file into the container
COPY target/product-service-1.0.0.jar app.jar
# Expose the port the app runs on
EXPOSE 5000
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
