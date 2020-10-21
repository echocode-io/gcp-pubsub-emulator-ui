package io.echocode.gcppubsubemulatorui.model.project;

import lombok.*;

import javax.annotation.Nullable;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionsResponse {
    @Nullable
    private List<Subscription> subscriptions;
}
