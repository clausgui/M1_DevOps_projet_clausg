FROM java:8

EXPOSE 8080

ADD /target/M1_DevOps_projet_clausg-1.0.jar projet.jar

RUN mvn install

ENTRYPOINT ["java", "-cp", "projet.jar", "clausg_projet.AppServeur"]
