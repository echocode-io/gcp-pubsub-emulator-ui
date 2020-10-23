package io.echocode.gcppubsubemulatorui.model.subscription.response;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionReceivedMessage {
    SubscriptionMessageData message;
}
