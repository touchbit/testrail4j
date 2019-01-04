FROM nginx:latest

COPY --from=andy23512/faketime-machine  /usr/local/lib/faketime/libfaketime.so.1 /usr/local/lib/faketime/libfaketime.so.1

ENV LD_PRELOAD=/usr/local/lib/faketime/libfaketime.so.1
ENV DONT_FAKE_MONOTONIC 1
ENV FAKETIME "2019-01-05 02:22:22"

ENV TR /var/www/testrail

RUN apt -yqq update
RUN apt -yqq install zip unzip wget

# TESTRAIL
RUN wget https://secure.gurock.com/downloads/testrail/testrail-latest-ion70.zip -O ./tr.zip
RUN unzip tr.zip -d /var/www/
RUN mkdir -p ${TR}/logs
RUN chmod -R 777 ${TR}
RUN chown www-data:www-data ${TR}
COPY .indirect/testrail/config.php ${TR}

# NGINX
COPY .indirect/testrail/nginx.conf /etc/nginx/
CMD ["nginx", "-g", "daemon off;"]