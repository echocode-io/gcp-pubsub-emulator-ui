package io.echocode.gcppubsubemulatorui.model.subscription.response;

import lombok.*;

import javax.annotation.Nullable;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PullSubscriptionResponse {
    @Nullable
    List<SubscriptionReceivedMessage> receivedMessages;
}
