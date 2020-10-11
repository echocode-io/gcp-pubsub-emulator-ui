package io.echocode.gcppubsubemulatorui.client;

import io.echocode.gcppubsubemulatorui.model.TopicsResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client(value = "${gcp.pubsub.emulator.ui.emulator-host}")
public interface PubSubClient {

    @Get(uri = "/v1/projects/{projectId}/topics", produces = MediaType.APPLICATION_JSON)
    TopicsResponse listTopics(String projectId);
}
