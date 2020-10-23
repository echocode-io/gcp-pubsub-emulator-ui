package io.echocode.gcppubsubemulatorui

import io.echocode.gcppubsubemulatorui.page.SubscriptionPage
import spock.util.concurrent.PollingConditions

class SubscriptionPageSpec extends PubSubBaseSpec {

    void 'view subscription page'() {
        given:
        PollingConditions conditions = new PollingConditions(timeout: 3)
        pubSubTestPublisher.send("""{"test":"hello"}""".bytes)
        browser.baseUrl = "http://${embeddedServer.host}:${embeddedServer.port}"

        when:
        to SubscriptionPage, new SubscriptionPage.ProjectTopicSubscriptionParams(project: "project1", topic: "topic2", subscription: "subscription2")

        then:
        at SubscriptionPage, { title == "project1/topic2/subscription2" }

        when:
        SubscriptionPage subscriptionPage = browser.page SubscriptionPage
        subscriptionPage.pullMessageButton.click();

        then:
        conditions.eventually {
            subscriptionPage.pulledMessages.size() == 1
            subscriptionPage.pulledMessages[0].text() == '{"test":"hello"}'
        }
    }
}
