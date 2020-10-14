package io.echocode.gcppubsubemulatorui.model;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class MessagePayload {
    @NotNull
    List<Message> messages;
}
