package io.echocode.gcppubsubemulatorui.controller;

import io.echocode.gcppubsubemulatorui.model.subscription.response.SubscriptionReceivedMessage;
import io.echocode.gcppubsubemulatorui.service.PubSubService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.View;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller("/project/{project}/topic/{topic}/subscription/{subscription}")
public class SubscriptionController {

    private final PubSubService pubSubService;

    @Inject
    public SubscriptionController(PubSubService pubSubService) {
        this.pubSubService = pubSubService;
    }

    @View("subscription")
    @Get
    public HttpResponse subscriptionView(@NotNull @NotEmpty final String project, @NotNull @NotEmpty final String topic, @NotNull @NotEmpty final String subscription) {
        return HttpResponse.ok(
                CollectionUtils.mapOf("project", project, "topic", topic, "subscription", subscription, "messages", Collections.emptyList())
        );
    }

    @View("subscription")
    @Post(consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse subscriptionPublishMessage(@NotNull final String project, @NotNull final String topic, @NotNull @NotEmpty final String subscription) {
        List<SubscriptionReceivedMessage> receivedMessages = pubSubService.pullMessages(project, subscription);
        log.info("Pulled messages from subscription {}/{}/{} - {}", project, topic, subscription, receivedMessages);
        return HttpResponse.ok(
                CollectionUtils.mapOf("project", project, "topic", topic, "subscription", subscription, "messages", receivedMessages)
        );
    }
}
