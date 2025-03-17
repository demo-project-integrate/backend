# Use Eclipse Temurin JDK 21 as the base image
FROM eclipse-temurin:21-jdk AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY . .

# Install Maven (if not included) and build the project
RUN apt-get update && apt-get install -y maven
RUN mvn clean install -DskipTests

# Use a minimal runtime image for better performance
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/product-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 5000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
