FROM openjdk:11
ADD ./target/userDTO-authentication-service-0.0.1-SNAPSHOT.war /usr/src/userDTO-authentication-service-0.0.1-SNAPSHOT.war
EXPOSE 8084
WORKDIR usr/src
ENTRYPOINT ["java","-jar","userDTO-authentication-service-0.0.1-SNAPSHOT.war"]
