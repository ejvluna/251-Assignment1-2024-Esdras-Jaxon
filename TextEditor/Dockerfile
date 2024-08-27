# Stage 1: Build the application using Maven
FROM eclipse-temurin:21-jdk AS builder

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and resolve dependencies
COPY pom.xml .
RUN mvn dependency:resolve

# Copy the source code and package the application
COPY src ./src
RUN mvn clean package

# Stage 2: Create the final image to run the application
FROM eclipse-temurin:21-jre

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the builder stage
COPY --from=builder /app/target/TextEditor-1.0-SNAPSHOT.jar /app/TextEditor-1.0-SNAPSHOT.jar

# Run the application
CMD ["java", "-jar", "TextEditor-1.0-SNAPSHOT.jar"]
