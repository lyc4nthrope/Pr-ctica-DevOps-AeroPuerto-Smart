# Stage 1: build — imagen oficial de Maven con JDK 17 incluido
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia solo el pom primero para aprovechar la cache de capas de Docker:
# si el código cambia pero las dependencias no, esta capa no se reconstruye
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests -B

# Stage 2: runtime — imagen liviana con solo JRE, sin código fuente ni JDK
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
