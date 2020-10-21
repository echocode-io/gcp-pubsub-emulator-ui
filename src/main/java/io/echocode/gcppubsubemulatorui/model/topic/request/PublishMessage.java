package io.echocode.gcppubsubemulatorui.model.topic.request;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class PublishMessage {

    byte[] data;

    public PublishMessage(@NotNull String data) {
        this.data = data.getBytes();
    }
}
