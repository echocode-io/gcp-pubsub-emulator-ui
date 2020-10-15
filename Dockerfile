FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-alpine-slim
COPY build/libs/gcp-pubsub-emulator-ui-*-all.jar gcp-pubsub-emulator-ui.jar
EXPOSE 8680
CMD java -jar gcp-pubsub-emulator-ui.jar
