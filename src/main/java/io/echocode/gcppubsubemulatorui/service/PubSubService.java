package io.echocode.gcppubsubemulatorui.service;


import io.echocode.gcppubsubemulatorui.client.PubSubClient;
import io.echocode.gcppubsubemulatorui.model.*;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
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
                .collect(Collectors.toMap(projectId -> projectId, projectId -> {
                    Map<String, List<Subscription>> subscriptions = Optional.ofNullable(httpClient.listSubscriptions(projectId).getSubscriptions())
                            .orElse(Collections.emptyList())
                            .stream()
                            .collect(Collectors.groupingBy(Subscription::getTopic));
                    return Optional.ofNullable(httpClient.listTopics(projectId).getTopics())
                            .orElse(Collections.emptyList())
                            .stream().peek(topic -> {
                                List<Subscription> topicSubscriptions = subscriptions.computeIfAbsent(topic.getName(), k -> new ArrayList<>());
                                topic.setSubscriptions(topicSubscriptions);
                            })
                            .collect(Collectors.toUnmodifiableList());
                }));
    }

    public PublishResponse publishMessage(String project, String topic, String data) {
        return httpClient.publishMessage(project, topic, new MessagePayload(Collections.singletonList(new Message(data))));
    }
}
