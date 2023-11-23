ARG BRANCH
FROM ghcr.io/gregoirebellon/thrift:${BRANCH} as thrift

FROM gradle:8-jdk17-alpine
LABEL org.opencontainers.image.source=https://github.com/GregoireBellon/M1-Architecture-Distribuee

WORKDIR /app

COPY --from=thrift /app/gen-java/internalcrm ./src/main/java/internalcrm
COPY . .

RUN --mount=type=cache,target=/root/.gradle gradle --no-daemon build testClasses -x test -x compileThrift

