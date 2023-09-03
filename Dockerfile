FROM gradle:8.1.1-jdk17 AS build
COPY --chown=gradle:gradle . /usr/src/app
WORKDIR /usr/src/app
RUN gradle build --no-daemon

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /usr/src/app/build/libs/*.jar /app/
CMD ["java", "-jar", "country-city-0.0.1-SNAPSHOT.jar"]