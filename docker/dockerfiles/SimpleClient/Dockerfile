ARG BRANCH
FROM ghcr.io/gregoirebellon/thrift:${BRANCH} as thrift

FROM node:21-alpine3.17
LABEL org.opencontainers.image.source=https://github.com/GregoireBellon/M1-Architecture-Distribuee

WORKDIR /app

COPY --from=thrift /app/gen-nodejs ./src/gen-nodejs
COPY package*.json ./

RUN --mount=type=cache,target=/root/.npm npm install

COPY . .
RUN npm run build:nothrift
