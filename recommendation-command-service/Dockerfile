FROM openjdk:11
ADD ./target/graph-0.0.1-SNAPSHOT.jar /usr/src/graph-0.0.1-SNAPSHOT.jar
EXPOSE 8080
WORKDIR usr/src
ENTRYPOINT ["java","-jar","graph-0.0.1-SNAPSHOT.jar"]
