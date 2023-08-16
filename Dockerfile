# Use uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Copie o JAR para dentro do container
COPY /target/doefacil-0.0.1-SNAPSHOT.jar /app/seu-arquivo.jar

# Comando para executar seu JAR
CMD ["java", "-jar", "/app/seu-arquivo.jar"]

EXPOSE 4001