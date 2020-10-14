package io.echocode.gcppubsubemulatorui

import io.echocode.gcppubsubemulatorui.page.HomePage

class HomePageSpec extends PubSubSpec {

    void 'test home page lists topics'() {
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
    }
}
