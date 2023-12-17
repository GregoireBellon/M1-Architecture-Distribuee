FROM node:21-alpine

WORKDIR /app/SimpleClient

RUN apk add thrift

COPY ./thrift /app/thrift
COPY ./SimpleClient /app/SimpleClient

RUN npm install && npm run build