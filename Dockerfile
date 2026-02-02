// Dockerfile Configuration/Instructions
FROM openjdk:21-jdk-slim    // base image with JDK
WORKDIR /app                // set working directory
COPY target/*.jar app.jar    // copy JAR into container
EXPOSE 9999                   // expose port on which application will run
ENTRYPOINT ["java","-jar","app.jar"]   // run the JAR



