FROM openjdk:11
ADD ./target/evaluation-service-0.0.1-SNAPSHOT.jar /event/src/evaluation-service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
WORKDIR event/src
ENTRYPOINT ["java","-jar","evaluation-service-0.0.1-SNAPSHOT.jar"]
