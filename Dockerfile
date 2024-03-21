FROM maven:3.8.4-openjdk-8 as builder
WORKDIR /app
COPY . /app
RUN mvn package
FROM openjdk:8
COPY --from=builder /app/web/target/*.war webapp-1.0-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "/app/webapp-1.0-SNAPSHOT.war"]