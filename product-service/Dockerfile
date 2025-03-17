# Build Stage
FROM eclipse-temurin:21-jdk AS build

# Set the working directory
WORKDIR /app

# Copy the entire project
COPY . .

# Install Maven (if not included) and build the parent POM
RUN apt-get update && apt-get install -y maven
RUN mvn clean install -pl .. -DskipTests  # Builds and installs the parent POM

# Build the product-service
RUN mvn clean install -DskipTests

# Runtime Stage
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/product-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 5000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
