FROM openjdk:19-jdk-alpine
WORKDIR /app
COPY target/Back_end-0.0.1-SNAPSHOT.war /app/Back_end.war
RUN apk update && apk upgrade
EXPOSE 8080
CMD ["java", "-jar", "/app/Back_end.war"]