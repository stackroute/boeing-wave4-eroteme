FROM openjdk:11
ADD ./target/spring-boot-websockets-1.0-SNAPSHOT.jar /usr/src/spring-boot-websockets-1.0-SNAPSHOT.jar
EXPOSE 8811
WORKDIR usr/src
ENTRYPOINT ["java","-jar","spring-boot-websockets-1.0-SNAPSHOT.jar"]
