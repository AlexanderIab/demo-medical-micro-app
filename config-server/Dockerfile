FROM openjdk

EXPOSE 8888

COPY build/libs/config-server-0.0.1-SNAPSHOT.jar /app/app.jar
COPY /src/main/resources/application.yml /app/application.yml
ENTRYPOINT ["java", "-jar", "/app/app.jar"]