package io.echocode.gcppubsubemulatorui.pubsub

import io.micronaut.gcp.pubsub.annotation.PubSubListener
import io.micronaut.gcp.pubsub.annotation.Subscription;

@PubSubListener
class PubSubTestListener {
    static private List<String> receivedMessages = []

    @Subscription("subscription1")
    void onMessage(byte[] data) {
        receivedMessages << new String(data)
    }

    static List<String> getReceivedMessages() {
        return receivedMessages;
    }
}