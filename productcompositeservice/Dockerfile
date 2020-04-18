FROM openjdk:12.0.2

WORKDIR /opt/app

EXPOSE 8080

ARG JAR_FILE=target/productcompositeservice-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]