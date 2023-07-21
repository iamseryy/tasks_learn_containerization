FROM eclipse-temurin:20-jre-alpine
MAINTAINER Sergei Perminov
ARG JAR_FILE=target/Hello-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} hello.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/hello.jar"]