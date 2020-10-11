package io.echocode.gcppubsubemulatorui.model;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TopicsResponse {
    List<Topic> topics;
}
