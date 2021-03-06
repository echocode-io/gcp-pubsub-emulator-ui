package io.echocode.gcppubsubemulatorui

import io.echocode.gcppubsubemulatorui.page.TopicPage
import spock.lang.Stepwise
import spock.util.concurrent.PollingConditions

@Stepwise
class TopicPageSpec extends PubSubBaseSpec {

    void 'view topic page'() {
        given:
        browser.baseUrl = "http://${embeddedServer.host}:${embeddedServer.port}"

        when:
        to TopicPage, new TopicPage.ProjectTopicParams(project: "project1", topic: "topic1")

        then:
        at TopicPage, { title == "project1/topic1" }

        when:
        TopicPage topicPage = browser.page TopicPage

        then:
        topicPage.topics.size() == 1
        topicPage.topics[0].text() == 'Topic: project1/topic1'
        topicPage.leads[0].text() == 'Publish a message'
        topicPage.publishMessageForm.displayed
    }

    void 'publish a message to topic'() {
        given:
        TopicPage topicPage = browser.page TopicPage
        String message = """{"test": "hi"}"""
        PollingConditions conditions = new PollingConditions(timeout: 3)

        when:
        topicPage.publishMessageArea.text = message
        topicPage.publishButton.click()

        then:
        conditions.eventually {
            pubSubTestListener.receivedMessages.size() == 1
            pubSubTestListener.receivedMessages[0] == message
        }
    }
}
