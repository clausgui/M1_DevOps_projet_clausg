FROM maven:3.3-jdk-8-onbuild

# RUN mvn install

# COPY /target/M1_DevOps_projet_clausg-1.0.jar projet.jar
COPY /usr/src/app/target/M1_DevOps_projet_clausg-1.0.jar projet.jar

ENTRYPOINT ["java", "-cp", "projet.jar", "clausg_projet.AppServeur"]
