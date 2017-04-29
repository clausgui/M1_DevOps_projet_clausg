FROM maven:3.3-jdk-8-onbuild

RUN mvn install

ADD /target/M1_DevOps_projet_clausg-1.0.jar projet.jar

ENTRYPOINT ["java", "-cp", "projet.jar", "clausg_projet.AppServeur"]
