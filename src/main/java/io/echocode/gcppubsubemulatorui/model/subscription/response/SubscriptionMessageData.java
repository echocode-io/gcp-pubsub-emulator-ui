package io.echocode.gcppubsubemulatorui.model.subscription.response;

import lombok.*;

import java.util.Base64;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionMessageData {
    String data;

    public String getData() {
        return new String(Base64.getDecoder().decode(this.data));
    }
}
