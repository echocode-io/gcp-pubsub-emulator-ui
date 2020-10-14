package io.echocode.gcppubsubemulatorui

import geb.spock.GebSpec
import groovy.util.logging.Slf4j
import io.echocode.gcppubsubemulatorui.container.PubSubEmulatorContainer
import io.micronaut.context.ApplicationContext
import io.micronaut.core.util.CollectionUtils
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.containers.wait.strategy.HostPortWaitStrategy
import spock.lang.AutoCleanup
import spock.lang.Shared

@Slf4j
@MicronautTest
class PubSubSpec extends GebSpec {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer

    @Shared
    @AutoCleanup
    ApplicationContext context

    @Shared
    @AutoCleanup
    static final GenericContainer<PubSubEmulatorContainer> container = new GenericContainer<PubSubEmulatorContainer>("messagebird/gcloud-pubsub-emulator:latest")
            .withEnv("PUBSUB_PROJECT1", "project1,topic1:subscription1")
            .withExposedPorts(8681);

    void setupSpec() {
        container.waitingFor(new HostPortWaitStrategy())
        container.start()
        container.followOutput(new Slf4jLogConsumer(log))
        List<Object> config = ["gcp.pubsub.emulator.ui.emulator-host", "http://${container.getHost()}:${container.getMappedPort(8681)}"]
        embeddedServer = ApplicationContext.run(EmbeddedServer,
                CollectionUtils.mapOf(
                        (config as Object[])
                )
        )
        println container.getMappedPort(8681)
        context = embeddedServer.getApplicationContext()
    }
}
