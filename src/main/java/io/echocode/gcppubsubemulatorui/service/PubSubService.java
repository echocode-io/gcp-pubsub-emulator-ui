package io.echocode.gcppubsubemulatorui.service;


import io.echocode.gcppubsubemulatorui.client.PubSubClient;
import io.echocode.gcppubsubemulatorui.model.Message;
import io.echocode.gcppubsubemulatorui.model.MessagePayload;
import io.echocode.gcppubsubemulatorui.model.PublishResponse;
import io.echocode.gcppubsubemulatorui.model.Topic;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
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

    public PublishResponse publishMessage(String project, String topic, String data) {
        return httpClient.publishMessage(project, topic, new MessagePayload(Collections.singletonList(new Message(data))));
    }
}
