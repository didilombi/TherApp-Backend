# Usa la imagen de OpenJDK 17
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado en el contenedor
COPY target/TherApp-1.jar app.jar

# Expone el puerto donde se ejecutará la aplicación
EXPOSE 9000

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]