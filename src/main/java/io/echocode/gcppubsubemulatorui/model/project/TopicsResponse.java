package io.echocode.gcppubsubemulatorui.model.project;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TopicsResponse {
    List<Topic> topics;
}
