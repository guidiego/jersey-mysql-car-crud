FROM mhart/alpine-node:latest

ADD ./ /webapp

WORKDIR /webapp

RUN cd /webapp \
    && npm install \
    && npm run build

CMD ["node", "server"]