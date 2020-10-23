package io.echocode.gcppubsubemulatorui


import geb.spock.GebSpec
import groovy.util.logging.Slf4j
import io.echocode.gcppubsubemulatorui.container.PubSubEmulatorContainer
import io.echocode.gcppubsubemulatorui.pubsub.PubSubTestListener
import io.echocode.gcppubsubemulatorui.pubsub.PubSubTestPublisher
import io.micronaut.context.ApplicationContext
import io.micronaut.core.util.CollectionUtils
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy
import spock.lang.AutoCleanup
import spock.lang.Shared

import javax.inject.Inject

@Slf4j
@MicronautTest
class PubSubBaseSpec extends GebSpec {

    @Inject
    PubSubTestListener pubSubTestListener

    @Inject
    PubSubTestPublisher pubSubTestPublisher

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer

    @Shared
    @AutoCleanup
    ApplicationContext context

    static PUBSUB_CONTAINER_HOST = ""

    @Shared
    @AutoCleanup
    public static final GenericContainer<PubSubEmulatorContainer> container = new GenericContainer<PubSubEmulatorContainer>("messagebird/gcloud-pubsub-emulator:latest")
            .withEnv("PUBSUB_PROJECT1", "project1,topic1:subscription1,topic2:subscription2")
            .withExposedPorts(8681);

    static {
        container.waitingFor(new HostPortWaitStrategy())
        container.start()
        container.followOutput(new Slf4jLogConsumer(log))
        PUBSUB_CONTAINER_HOST = "${container.getHost()}:${container.getMappedPort(8681)}"
    }

    void setupSpec() {
        List<Object> config = [
                "pubsub.emulator.host", PUBSUB_CONTAINER_HOST
        ]
        embeddedServer = ApplicationContext.run(EmbeddedServer,
                CollectionUtils.mapOf(
                        (config as Object[])
                )
        )
        context = embeddedServer.getApplicationContext()
    }
}
