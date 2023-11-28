ARG BRANCH
FROM ghcr.io/gregoirebellon/virtual:${BRANCH} as base

FROM gradle:8-jdk17-alpine
LABEL org.opencontainers.image.source=https://github.com/GregoireBellon/M1-Architecture-Distribuee

WORKDIR /app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

USER appuser

COPY --from=base --chown=appuser:appgroup /app/build/libs/VirtualCRM-0.0.1-SNAPSHOT.jar app.jar

ARG PORT=8090
ENV PORT=$PORT

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar" ]
