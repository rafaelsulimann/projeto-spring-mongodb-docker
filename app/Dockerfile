FROM openjdk:19-slim-bullseye
EXPOSE 8080
ADD target/project-springboot-nosql-mongodb-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=${MONGODB_URI}", "-jar","/app.jar"]