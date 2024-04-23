FROM openjdk:17
WORKDIR /sobesTask
COPY out/artifacts/SobesTask_jar/SobesTask.jar /sobesTask/SobesTask.jar
EXPOSE 8082
CMD ["java", "-jar", "SobesTask.jar"]