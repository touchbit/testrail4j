FROM php:7.2-fpm
ENV VERSION 7.2

RUN apt-get update
RUN apt-get install -y netcat
RUN apt-get install -y wget
RUN wget http://downloads3.ioncube.com/loader_downloads/ioncube_loaders_lin_x86-64.tar.gz -O ioncube.tar.gz
RUN tar -xvvzf ioncube.tar.gz
RUN mv ioncube/ioncube_loader_lin_${VERSION}.so `php-config --extension-dir`
RUN rm -Rf ioncube.tar.gz ioncube
RUN docker-php-ext-enable ioncube_loader_lin_${VERSION}
RUN docker-php-ext-install mysqli