package io.echocode.gcppubsubemulatorui.model.project;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic {
    @NotNull
    String name;

    @NotNull
    @Setter
    List<Subscription> subscriptions;

    public String topicNameStripped() {
        return name.split("/")[3];
    }
}
