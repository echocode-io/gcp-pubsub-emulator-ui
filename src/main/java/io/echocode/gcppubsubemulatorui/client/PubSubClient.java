package io.echocode.gcppubsubemulatorui.client;

import io.echocode.gcppubsubemulatorui.model.project.SubscriptionsResponse;
import io.echocode.gcppubsubemulatorui.model.project.TopicsResponse;
import io.echocode.gcppubsubemulatorui.model.subscription.request.PullSubscriptionRequest;
import io.echocode.gcppubsubemulatorui.model.subscription.response.PullSubscriptionResponse;
import io.echocode.gcppubsubemulatorui.model.topic.request.PublishMessageRequest;
import io.echocode.gcppubsubemulatorui.model.topic.response.PublishResponse;
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
    PublishResponse publishMessage(String projectId, String topic, @Body PublishMessageRequest message);

    @Post(uri = "/v1/projects/{projectId}/subscriptions/{subscription}:pull", produces = MediaType.APPLICATION_JSON)
    PullSubscriptionResponse pullMessages(String projectId, String subscription, @Body PullSubscriptionRequest message);
}
