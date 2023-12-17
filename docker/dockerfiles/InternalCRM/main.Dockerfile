FROM gradle:8-jdk17-alpine

WORKDIR /app/InternalCRM

RUN apk add thrift

COPY ./thrift /app/thrift
COPY ./InternalCRM /app/InternalCRM

RUN gradle --no-daemon clean assemble