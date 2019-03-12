FROM openjdk:11

ADD ./target/userProfile-0.0.1-SNAPSHOT.jar /event/src/userProfile-0.0.1-SNAPSHOT.jar
EXPOSE 8091
WORKDIR event/src
ENTRYPOINT ["java","-jar","userProfile-0.0.1-SNAPSHOT.jar"]