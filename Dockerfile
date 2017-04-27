FROM ubuntu:14.04

RUN apt-get update
#### installing JDK

RUN apt-get install -y openjdk-7-jdk

ADD target/M1_DevOps_projet_clausg-1.0.jar file.jar

ENTRYPOINT ["java", "-cp", "file.jar", "clausg_projet.AppServeur"]
