FROM maven:3-jdk-11 AS build
ARG service
RUN if [ -z "$service" ]; then echo "Service is not set!"; exit 1; fi
COPY . /app
WORKDIR /app/${service}
RUN mvn install -DskipTests=true

FROM openjdk:8-jdk-alpine
ARG service
ARG version
RUN if [ -z "version" ]; then echo "Version is not set!"; exit 1; fi
COPY --from=build app/${service}/target/${service}-${version}.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]