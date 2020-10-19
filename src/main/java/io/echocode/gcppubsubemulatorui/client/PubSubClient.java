package io.echocode.gcppubsubemulatorui.client;

import io.echocode.gcppubsubemulatorui.model.MessagePayload;
import io.echocode.gcppubsubemulatorui.model.PublishResponse;
import io.echocode.gcppubsubemulatorui.model.SubscriptionsResponse;
import io.echocode.gcppubsubemulatorui.model.TopicsResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client(value = "http://${pubsub.emulator.host}")
public interface PubSubClient {

    @Get(uri = "/v1/projects/{projectId}/topics", produces = MediaType.APPLICATION_JSON)
    TopicsResponse listTopics(String projectId);

    @Get(uri = "/v1/projects/{projectId}/subscriptions", produces = MediaType.APPLICATION_JSON)
    SubscriptionsResponse listSubscriptions(String projectId);

    @Post(uri = "/v1/projects/{projectId}/topics/{topic}:publish", produces = MediaType.APPLICATION_JSON)
    PublishResponse publishMessage(String projectId, String topic, @Body MessagePayload message);
}
