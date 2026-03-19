# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# copy source code
COPY src ./src

# build the jar
RUN mvn -q -DskipTests package

# -------- RUNTIME STAGE --------
FROM eclipse-temurin:17-jre
WORKDIR /app

# copy built jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]