FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -Dskiptests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/backendservice-0.0.1-SNAPSHOT.jar backendservice.jar
EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "echo 'DEBUG: Spring URL is:' $SPRING_DATASOURCE_URL && java -jar backendservice.jar"]
