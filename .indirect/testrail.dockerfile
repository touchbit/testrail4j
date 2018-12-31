FROM nginx:latest

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