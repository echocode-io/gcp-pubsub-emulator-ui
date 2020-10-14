package io.echocode.gcppubsubemulatorui.controller;

import io.echocode.gcppubsubemulatorui.model.PublishResponse;
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

@Slf4j
@Controller("/project/{project}/topic/{topic}")
public class TopicController {

    private final PubSubService pubSubService;

    @Inject
    public TopicController(PubSubService pubSubService) {
        this.pubSubService = pubSubService;
    }

    @View("topic")
    @Get
    public HttpResponse topicView(@NotNull @NotEmpty final String project, @NotNull @NotEmpty final String topic) {
        return HttpResponse.ok(
                CollectionUtils.mapOf("project", project, "topic", topic)
        );
    }

    @View("topic")
    @Post(consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse topicPublishMessage(@NotNull final String project, @NotNull final String topic, @NotNull @NotEmpty final String message) {
        PublishResponse publishResponse = pubSubService.publishMessage(project, topic, message);
        log.info("Published message {} to topic {}/{} - message ID: {}", message, project, topic, publishResponse.getMessageIds().get(0));
        return HttpResponse.ok(
                CollectionUtils.mapOf("project", project, "topic", topic, "message", message, "messageIds", publishResponse.getMessageIds())
        );
    }
}
