FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /opt/demo

COPY pom.xml .
RUN mvn dependency:go-offline

COPY ./src ./src
RUN mvn clean package


FROM eclipse-temurin:21-jre-jammy AS runner


COPY --from=builder /opt/demo/target/web-service-jpa-0.0.1-SNAPSHOT.jar .
EXPOSE 8080


ENTRYPOINT ["java", "-jar","web-service-jpa-0.0.1-SNAPSHOT.jar"]
