ARG BRANCH
FROM ghcr.io/gregoirebellon/internal:${BRANCH} as base

FROM gradle:8-jdk17-alpine
LABEL org.opencontainers.image.source=https://github.com/GregoireBellon/M1-Architecture-Distribuee

WORKDIR /app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

USER appuser
COPY --from=base --chown=appuser:appgroup /app/build/libs/InternalCRM.jar app.jar

CMD [ "java", "-jar", "app.jar" ]
