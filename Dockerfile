FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app
COPY . .
COPY .mvn .mvn
RUN ./mvnw clean
RUN ./mvnw package

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]