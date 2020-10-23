package io.echocode.gcppubsubemulatorui.model.subscription.request;

import lombok.Value;

@Value
public class PullSubscriptionRequest {
    Boolean returnImmediately;
    Integer maxMessages;
}
