FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /opt/demo
COPY pom.xml .
RUN mvn dependency:go-offline
