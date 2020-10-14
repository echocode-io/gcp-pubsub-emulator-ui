package io.echocode.gcppubsubemulatorui

import io.echocode.gcppubsubemulatorui.page.TopicPage

class TopicPageSpec extends PubSubSpec {

    void 'publish a message to topic'() {
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
}
