# Use Eclipse Temurin JDK 21 as the base image
FROM eclipse-temurin:21-jdk

# Define build-time variable (ARG)
ARG DOCKER_PORT
ARG DB_USER
ARG DB_PASS

# Define runtime environment variables (ENV)
ENV PORT=${DOCKER_PORT}
ENV DB_USER=${DB_USER}
ENV DB_PASS=${DB_PASS}

# Set the working directory inside the container
WORKDIR /app
# Copy the Maven build JAR file into the container
COPY target/product-service-1.0.0.jar app.jar
# Expose the port the app runs on
EXPOSE ${DOCKER_PORT}
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${DOCKER_PORT}"]

