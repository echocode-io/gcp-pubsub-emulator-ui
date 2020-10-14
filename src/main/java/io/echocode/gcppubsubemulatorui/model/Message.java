package io.echocode.gcppubsubemulatorui.model;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Base64;

@Value
public class Message {

    byte[] data;

    public Message(@NotNull String data) {
        this.data = Base64.getEncoder().encode(data.getBytes());
    }
}
