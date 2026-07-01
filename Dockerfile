# Etapa 1: Construcción (Trae una máquina con Maven y Java 21 oficial)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Compilamos saltando los tests para mayor velocidad
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Una máquina ultra ligera solo con Java 21)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos el archivo .jar que se acaba de crear en la Etapa 1
COPY --from=build /app/target/dgac-ms-usuarios-0.0.1-SNAPSHOT.jar app.jar
# Exponemos el puerto
EXPOSE 8081
# Comando para encender el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]