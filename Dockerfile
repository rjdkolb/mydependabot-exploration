FROM azul/zulu-openjdk:15.0.1

ARG JAR_FILE=target/app.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]
