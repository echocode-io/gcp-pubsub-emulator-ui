package io.echocode.gcppubsubemulatorui.model.project;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscription {
    @NotNull
    private String name;
    @NotNull
    private String topic;
    @NotNull
    private Integer ackDeadlineSeconds;
    @NotNull
    private String messageRetentionDuration;

    public String subscriptionNameStripped() {
        return this.name.split("/")[3];
    }
}
