FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw

COPY src src
RUN ./mvnw --batch-mode --no-transfer-progress clean package

FROM eclipse-temurin:21-jre

WORKDIR /app
RUN groupadd --system app && useradd --system --gid app app
COPY --from=build --chown=app:app /workspace/target/wacdo-*.jar /app/app.jar

USER app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
