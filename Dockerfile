FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /target/backendservice-0.0.1-SNAPSHOT.jar backendservice.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","backendservice.jar"]
