# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# copy pom.xml and download dependencies (to use Docker layer cache)
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# copy source code
COPY src ./src

# build and FORCE repackage to create bootable jar (not *-original.jar)
RUN mvn -q -DskipTests clean package spring-boot:repackage

# -------- RUNTIME STAGE --------
FROM eclipse-temurin:17-jre
WORKDIR /app

# copy ONLY the re-packaged boot jar (adjust glob if your artifact isn't SNAPSHOT)
COPY --from=build /app/target/*-SNAPSHOT.jar /app/app.jar
# If your artifact is a release (no SNAPSHOT), use:
# COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]