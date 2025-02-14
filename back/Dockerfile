FROM openjdk:17-alpine
LABEL authors="dmitry voicu 2025"
WORKDIR /app
COPY target/to-do-list-0.0.1-SNAPSHOT.jar /app/app.jar
RUN rm -rf /var/cache/apk/*
ENTRYPOINT ["java", "-jar", "app.jar"]
