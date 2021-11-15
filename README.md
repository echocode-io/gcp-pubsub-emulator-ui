
# gcp-pubsub-emulator-ui

A UI console for the Google Cloud Platform PubSub emulator.

<img width="680" alt="topics list" src="https://user-images.githubusercontent.com/629600/96189433-61e64c00-0f38-11eb-8fed-7aef70b9e399.png">

<img width="680" alt="topic page" src="https://user-images.githubusercontent.com/629600/96189532-8e9a6380-0f38-11eb-8b64-585591337bac.png">

## Getting started

```shell script
docker run --rm -p 8680:8680 --env PUBSUB_EMULATOR_HOST=<emulator-host>:<emulator-port> --env GCP_PROJECT_IDS=<comma-separated-project-ids> echocode/gcp-pubsub-emulator-ui:latest
```

The UI runs on [http://localhost:8680](localhost:8680) by default.

### Running by pointing at local emulator

```shell script
docker run --rm -p 8680:8680 --env PUBSUB_EMULATOR_HOST=host.docker.internal:8681 --env GCP_PROJECT_IDS=company-dev,company-staging echocode/gcp-pubsub-emulator-ui:latest
```

### Running in an existing docker-compose.yaml

```yaml
version: "3.8"
services:
  ...
  pubsub-emulator-ui:
    image: echocode/gcp-pubsub-emulator-ui:latest
    ports:
      - "8680:8680"
    environment:
      - PUBSUB_EMULATOR_HOST=pubsub-emulator:8681
      - GCP_PROJECT_IDS=company-dev,company-staging
```


### Running a complete example

Spin up a local emulator in Docker (using [marcelcorso/gcloud-pubsub-emulator](https://github.com/marcelcorso/gcloud-pubsub-emulator)) with projects, topics, subscriptions and this UI console:

```yaml
version: "3.8"
services:

  pubsub-emulator:
    image: messagebird/gcloud-pubsub-emulator:latest
    ports:
      - "8681:8681"
    environment:
      - PUBSUB_PROJECT1=company-dev,invoices:invoice-calculator,chats:slack-out:irc-out,notifications
      - PUBSUB_PROJECT2=company-staging,invoices:invoice-calculator

  pubsub-emulator-ui:
    image: echocode/gcp-pubsub-emulator-ui:latest
    ports:
      - "8680:8680"
    environment:
      - PUBSUB_EMULATOR_HOST=pubsub-emulator:8681
      - GCP_PROJECT_IDS=company-dev,company-staging
```

# Local development

* Install Java 11

* Clone the project: `git@github.com:echocode-io/gcp-pubsub-emulator-ui.git`

* Build and run tests: `./gradlew clean build`

# Built with

* [Micronaut](https://micronaut.io/) as the webserver.
* [Geb](https://gebish.org/) and [Spock](http://spockframework.org/) for automated testing.
* [marcelcorso/gcloud-pubsub-emulator](https://github.com/marcelcorso/gcloud-pubsub-emulator) for simulating and testing the emulator.
