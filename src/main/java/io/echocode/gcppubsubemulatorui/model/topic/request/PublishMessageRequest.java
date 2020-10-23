package io.echocode.gcppubsubemulatorui.model.topic.request;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class PublishMessageRequest {
    @NotNull
    List<PublishMessage> messages;
}
