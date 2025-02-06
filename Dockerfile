# Usa una imagen oficial de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado por Maven
COPY target/TherApp-1.jar app.jar

# Expone el puerto 9000 para el backend
EXPOSE 9000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
