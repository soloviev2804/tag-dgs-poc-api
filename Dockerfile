#FROM gradle:jdk17 AS cache
#WORKDIR /app
#ENV GRADLE_USER_HOME /cache
#COPY build.gradle.kts settings.gradle.kts ./
#RUN gradle --no-daemon build --stacktrace
#
#FROM gradle:jdk17 AS builder
#WORKDIR /app
#COPY --from=cache /cache /home/gradle/.gradle
#COPY src/ src/
#RUN gradle --no-daemon build --stacktrace
#
#FROM openjdk:17
#WORKDIR /app
#COPY --from=builder /app/build/libs/tag-dgs-poc-api-0.0.1-SNAPSHOT.jar /tag-dgs-poc-api-0.0.1-SNAPSHOT.jar
#ENV PORT 8080
#EXPOSE 80
#HEALTHCHECK --timeout=5s --start-period=5s --retries=1 \
#    CMD curl -f http://localhost:$PORT/health_check
#CMD ["java", "-jar", "-Dspring.profiles.active=default", "/tag-dgs-poc-api-0.0.1-SNAPSHOT.jar"]
FROM gradle:jdk17
WORKDIR /app
COPY ./ /app

ENTRYPOINT ./gradlew bootRun -Dtest.skip