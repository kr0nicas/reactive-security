FROM openjdk:17-ea-14-jdk-alpine3.13
ENV APP_FILE config-catalog-service-1.0.0.jar
ENV APP_HOME /usr/app
EXPOSE 8000
COPY target/*.jar $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]