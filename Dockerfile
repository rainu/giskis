FROM openjdk:8-jre-alpine
MAINTAINER rainu <rainu@raysha.de>

RUN apk add --update bash postgresql-client &&\
 rm -rf /var/cache/apk/*

COPY docker/wait_and_start.sh /giskis.sh
COPY target/giskis*.jar /giskis.jar

CMD ["/giskis.sh"]