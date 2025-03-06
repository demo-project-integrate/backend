FROM openjdk:21
EXPOSE 8085
ADD target/backend.jar backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]
