FROM alpine:3.18.4

WORKDIR /app

RUN apk add --no-cache thrift

COPY ./src/LeadService.thrift .

RUN thrift -o . --gen js:node,ts LeadService.thrift
RUN thrift -o . --gen java LeadService.thrift

LABEL org.opencontainers.image.source=https://github.com/GregoireBellon/M1-Architecture-Distribuee
