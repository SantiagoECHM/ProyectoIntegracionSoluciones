#FROM mysql

#COPY script.sql /docker/entrypoint-initdb.d/script.sql

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
ADD ./target/ventas-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]



