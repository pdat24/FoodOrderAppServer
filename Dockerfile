FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/food_order_app-1.jar app.jar

ENTRYPOINT ["java", ".jar", "app.jar"]
