FROM openjdk:17

WORKDIR /pandora

CMD ["./gradlew", "clean", "bootJar"]

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]