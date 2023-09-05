FROM openjdk:17-alpine
WORKDIR /app
COPY ./build/libs/country-city-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "country-city-0.0.1-SNAPSHOT.jar"]