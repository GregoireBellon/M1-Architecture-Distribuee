ARG BRANCH
FROM ghcr.io/gregoirebellon/virtual:${BRANCH} as base

FROM gradle:8-jdk17-alpine
LABEL org.opencontainers.image.source=https://github.com/GregoireBellon/M1-Architecture-Distribuee

WORKDIR /app
RUN groupadd -g 10001 appuser && \
    useradd -u 10000 -g appuser appuser \
    && chown -R appuser:appuser /app
USER appuser

COPY --from=base --chown=appuser:appuser /app/build/libs/VirtualCRM-0.0.1-SNAPSHOT.jar app.jar

CMD [ "java", "-jar", "app.jar" ]
