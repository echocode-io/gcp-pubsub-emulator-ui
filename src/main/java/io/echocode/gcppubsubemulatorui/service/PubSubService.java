package io.echocode.gcppubsubemulatorui.service;


import io.echocode.gcppubsubemulatorui.client.PubSubClient;
import io.echocode.gcppubsubemulatorui.model.Topic;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class PubSubService {

    @Client
    @Inject
    PubSubClient httpClient;

    @Value("${gcp.pubsub.emulator.ui.projects}")
    List<String> projectIds;

    public Map<String, List<Topic>> listTopicsByProject() {
        return projectIds.stream()
                .collect(Collectors.toMap(projectId -> projectId, projectId -> httpClient.listTopics(projectId).getTopics()));
    }
}
