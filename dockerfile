FROM openjdk:8-jdk-alpine
COPY target/SpringBoot-0.0.1-SNAPSHOT.jar SpringBoot-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "SpringBoot-0.0.1-SNAPSHOT.jar"]