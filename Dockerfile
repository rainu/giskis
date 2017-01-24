FROM alpine:3.3
MAINTAINER rainu <rainu@raysha.de>

RUN apk add --update bash postgresql-client redis &&\
 rm -rf /var/cache/apk/*

COPY src/giskis/giskis /bin/
COPY docker/worker.sh /bin/

CMD ["worker.sh"]