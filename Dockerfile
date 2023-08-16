# Usando uma imagem base com Maven e JDK 17 para realizar o build
FROM maven:3.8.1-openjdk-17 AS build

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o pom.xml e os arquivos fonte para a imagem
COPY pom.xml .
COPY src ./src/

# Realizando o build com Maven
RUN mvn clean package

# Utilizando a imagem de OpenJDK 17 para rodar a aplicação
FROM openjdk:17-jre-slim

# Copiando o JAR resultante do build para a nova imagem
COPY --from=build /app/target/doefacil-0.0.1-SNAPSHOT.jar /app/seu-arquivo.jar

# Definindo o comando padrão para rodar a aplicação
CMD ["java", "-jar", "/app/seu-arquivo.jar"]