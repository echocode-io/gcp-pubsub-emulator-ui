package io.echocode.gcppubsubemulatorui

import io.echocode.gcppubsubemulatorui.page.HomePage
import io.echocode.gcppubsubemulatorui.page.TopicPage
import spock.lang.Stepwise

@Stepwise
class HomePageBaseSpec extends PubSubBaseSpec {

    void 'home page lists topics'() {
        given:
        browser.baseUrl = "http://${embeddedServer.host}:${embeddedServer.port}"

        when:
        to HomePage

        then:
        at HomePage

        when:
        HomePage homePage = browser.page HomePage

        then:
        homePage.topics.size() == 1
        homePage.topics[0].text() == 'topic1'
        homePage.subscriptions[0].text() == 'subscription1'
    }

    void 'can navigate to topic'() {
        given:
        HomePage homePage = browser.page HomePage

        when:
        homePage.topics[0].click()

        then:
        at TopicPage, { title == "project1/topic1" }
    }
}
