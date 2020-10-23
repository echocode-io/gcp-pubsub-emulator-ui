package io.echocode.gcppubsubemulatorui.listener

import io.micronaut.gcp.pubsub.annotation.PubSubClient
import io.micronaut.gcp.pubsub.annotation.Topic

@PubSubClient
interface PubSubTestPublisher {
    @Topic("topic1")
    void send(byte[] data);
}
