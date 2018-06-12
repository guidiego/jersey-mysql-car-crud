FROM java:latest

ADD ./ /api

WORKDIR /api

RUN cd /api \
    && ./mvnw clean install

CMD ["java", "-jar", "target/jersey-jetty-1.0-SNAPSHOT.jar"]