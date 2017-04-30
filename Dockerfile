FROM maven:3.3-jdk-8-onbuild

LABEL maintainer "GuiΩ <guillaume.claus@worldonline.fr>"
#RUN mvn install

COPY target/M1_DevOps_projet_clausg-1.0.jar projet.jar
# COPY M1_DevOps_projet_clausg-1.0.jar projet.jar

EXPOSE 5342

ENTRYPOINT ["java", "-cp", "projet.jar", "clausg_projet.serveur.AppServeur"]
