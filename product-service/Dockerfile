# Build Stage
FROM eclipse-temurin:21-jdk AS build

WORKDIR /

# Copy the entire backend project (including parent POM)
COPY . .

# Install Maven (if needed) and build the parent POM
RUN apt-get update && apt-get install -y maven
RUN mvn clean install -f ./pom.xml -DskipTests  # Builds and installs the parent POM

# Build the product-service
WORKDIR /app/product-service
RUN mvn clean install -DskipTests

# Runtime Stage
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/product-service/target/product-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
