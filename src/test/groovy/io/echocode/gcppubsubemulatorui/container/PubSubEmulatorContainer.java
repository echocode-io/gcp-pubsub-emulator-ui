package io.echocode.gcppubsubemulatorui.container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class PubSubEmulatorContainer extends GenericContainer<PubSubEmulatorContainer> {

    public PubSubEmulatorContainer(String imageName) {
        super(DockerImageName.parse(imageName));
    }
}