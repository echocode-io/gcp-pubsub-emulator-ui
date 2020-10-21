package io.echocode.gcppubsubemulatorui.model.topic.response;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PublishResponse {

    @NotNull
    @NotEmpty
    List<String> messageIds;
}
