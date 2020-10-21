package io.echocode.gcppubsubemulatorui.controller;

import io.echocode.gcppubsubemulatorui.model.project.Topic;
import io.echocode.gcppubsubemulatorui.service.PubSubService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller("/")
class ViewsController {

    private final PubSubService pubSubService;

    @Inject
    ViewsController(PubSubService pubSubService) {
        this.pubSubService = pubSubService;
    }

    @View("home")
    @Get
    public HttpResponse home() {
        Map<String, List<Topic>> projectTopics = pubSubService.listTopicsByProject();
        log.info("projectTopics {}", projectTopics);
        return HttpResponse.ok(
                CollectionUtils.mapOf("projectTopics", projectTopics)
        );
    }
}
