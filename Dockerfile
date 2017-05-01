FROM maven:3.5-jdk-7-onbuild

LABEL maintainer "GuiΩ <guillaume.claus@worldonline.fr>"
#RUN mvn install
# RUN ls target/

COPY target/M1_DevOps_projet_clausg-1.0.jar projet.jar

# COPY M1_DevOps_projet_clausg-1.0.jar projet.jar

EXPOSE 5342

ENTRYPOINT ["java", "-cp", "projet.jar", "clausg_projet.serveur.AppServeur"]
