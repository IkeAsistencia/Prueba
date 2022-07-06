FROM openjdk:8
ADD target/DockerDemo.jar DockerDemo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","DockerDemo.jar"]