FROM openjdk:8
COPY ./target/ExcellConvertor-0.0.1-SNAPSHOT.jar ExcellConvertor-0.0.1-SNAPSHOT.jar
EXPOSE 8900
ENTRYPOINT ["java","-jar", "ExcellConvertor-0.0.1-SNAPSHOT.jar"]