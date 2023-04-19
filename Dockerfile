FROM openjdk:11-jre-slim

EXPOSE 5011

COPY build/libs/shoppingmall-0.0.1-SNAPSHOT.war .

CMD java -jar shoppingmall-0.0.1-SNAPSHOT.war
