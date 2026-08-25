FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jre-alpine AS runtime

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

# Copy ONLY the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# H2 file-based DB persistence (optional volume mount)
RUN mkdir -p /app/data && chown -R appuser:appgroup /app

USER appuser

# Expose Spring Boot default port
EXPOSE 8080

# JVM tuning for containers (Java 21)
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:+UseG1GC"

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]