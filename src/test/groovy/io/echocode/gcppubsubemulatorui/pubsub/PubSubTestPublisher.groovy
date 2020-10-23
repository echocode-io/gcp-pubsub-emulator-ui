package io.echocode.gcppubsubemulatorui.pubsub

import io.micronaut.gcp.pubsub.annotation.PubSubClient
import io.micronaut.gcp.pubsub.annotation.Topic

@PubSubClient
interface PubSubTestPublisher {
    @Topic("topic2")
    void send(byte[] data);
}
