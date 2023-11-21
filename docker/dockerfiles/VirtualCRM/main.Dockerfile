FROM gradle:8.3-jdk17-alpine

WORKDIR /app/VirtualCRM

RUN apk add thrift

COPY ./thrift /app/thrift
COPY ./VirtualCRM /app/VirtualCRM

RUN gradle clean assemble