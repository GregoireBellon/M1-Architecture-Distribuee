ARG BRANCH
FROM ghcr.io/gregoirebellon/thrift:${BRANCH} as thrift

FROM node:21-alpine3.17

WORKDIR /app

COPY --from=thrift /app/gen-nodejs ./src/gen-nodejs
COPY package*.json ./
RUN --mount=type=cache,target=/root/.npm npm install
COPY . .
RUN npm run build:nothrift
