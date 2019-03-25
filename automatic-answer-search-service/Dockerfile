FROM openjdk:11
ADD ./target/AutomaticAnswerSearchService-0.0.1-SNAPSHOT.jar /usr/src/AutomaticAnswerSearchService-0.0.1-SNAPSHOT.jar
EXPOSE 8094
WORKDIR usr/src
ENTRYPOINT ["java","-jar","AutomaticAnswerSearchService-0.0.1-SNAPSHOT.jar"]