package io.echocode.gcppubsubemulatorui.model;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class Message {

    byte[] data;

    public Message(@NotNull String data) {
        this.data = data.getBytes();
    }
}
