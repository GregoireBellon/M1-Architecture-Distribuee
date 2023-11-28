ARG BRANCH
FROM ghcr.io/gregoirebellon/internal:${BRANCH} as base

FROM gradle:8-jdk17-alpine
LABEL org.opencontainers.image.source=https://github.com/GregoireBellon/M1-Architecture-Distribuee

WORKDIR /app
RUN groupadd -g 10001 appuser && \
    useradd -u 10000 -g appuser appuser \
    && chown -R appuser:appuser /app
USER appuser
COPY --from=base --chown=appuser:appuser /app/build/libs/InternalCRM.jar app.jar

CMD [ "java", "-jar", "app.jar" ]
