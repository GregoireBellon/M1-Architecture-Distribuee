ARG BRANCH
FROM ghcr.io/gregoirebellon/virtual:${BRANCH} as base

FROM gradle:8-jdk17-alpine
LABEL org.opencontainers.image.source=https://github.com/GregoireBellon/M1-Architecture-Distribuee

WORKDIR /app
USER java
COPY --from=base --chown=java:nogroup /app/build/libs/VirtualCRM-0.0.1-SNAPSHOT.jar app.jar

CMD [ "java", "-jar", "app.jar" ]
