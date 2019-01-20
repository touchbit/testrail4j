FROM galilee/sphinx-build

MAINTAINER Oleg Shaburov "shaburov.o.a@gmail.com"

COPY ./docs /root/docs
RUN sphinx-build -W -b html /root/docs /root/site

FROM nginx

RUN rm -rf /usr/share/nginx/html/*
COPY --from=0 /root/site/ /usr/share/nginx/html/