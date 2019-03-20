FROM openjdk:11
ADD ./target/evaluation-service-0.0.1-SNAPSHOT.war /event/src//target/evaluation-service-0.0.1-SNAPSHOT.war
EXPOSE 8082
WORKDIR event/src
ENTRYPOINT ["java","-war","evaluation-service-0.0.1-SNAPSHOT.war"]
