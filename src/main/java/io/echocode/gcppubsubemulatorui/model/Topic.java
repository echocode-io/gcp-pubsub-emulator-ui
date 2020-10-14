package io.echocode.gcppubsubemulatorui.model;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic {
    String name;

    public String topicNameStripped() {
        return name.split("/")[3];
    }
}
