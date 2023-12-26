FROM openjdk:19-jdk-alpine
LABEL authors="maximyasnogorodskiy"

COPY target/*.jar jira.jar
COPY resources ./resources
EXPOSE 8080

ENV DATABASE_PASSWORD=maxim1100
ENV DATABASE_USERNAME=maximyasn
ENV MAIL_PASSWORD=zdfzsrqvgimldzyj
ENV MAIL_USERNAME=jira4jr@gmail.com

ENTRYPOINT ["java","-jar","/jira.jar", "spring.profiles.active=prod"]