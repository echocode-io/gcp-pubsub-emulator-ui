package io.echocode.gcppubsubemulatorui.factory

import com.google.api.gax.core.CredentialsProvider
import com.google.api.gax.core.NoCredentialsProvider
import com.google.api.gax.grpc.GrpcTransportChannel
import com.google.api.gax.rpc.FixedTransportChannelProvider
import com.google.api.gax.rpc.TransportChannelProvider
import io.echocode.gcppubsubemulatorui.PubSubBaseSpec
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.gcp.Modules

import javax.inject.Named
import javax.inject.Singleton

@Factory
class IntegrationTestFactory {

    @Replaces(CredentialsProvider)
    @Singleton
    @Named(Modules.PUBSUB)
    CredentialsProvider credentialsProvider() {
        return NoCredentialsProvider.create()
    }

    @Replaces(TransportChannelProvider)
    @Singleton
    @Named(Modules.PUBSUB)
    TransportChannelProvider transportChannelProvider(CredentialsProvider credentialsProvider) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(PubSubBaseSpec.PUBSUB_CONTAINER_HOST).usePlaintext().build()
        TransportChannelProvider channelProvider =
                FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel))
        return channelProvider
    }
}
