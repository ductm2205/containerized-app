# FROM eclipse-temurin:17-jdk AS build
# WORKDIR /app
# COPY . /app
# RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-noble
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# COPY --from=build /app/Atarget/*.jar app.jar
COPY /target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]