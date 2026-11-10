FROM maven:3.9-eclipse-temurin-22 AS builder
WORKDIR /app

# On copie d'abord uniquement le pom pour profiter du cache Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# On copie le code source et on le compile
COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:22-jre-alpine
WORKDIR /app

# Utilisateur non-root
RUN addgroup -S spring && adduser -S spring -G spring

# On recupère uniquement le JAR généré
COPY --from=builder /app/target/*.jar app.jar

# On definit les permissions
RUN chown spring:spring app.jar
USER spring

# On démarre l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
