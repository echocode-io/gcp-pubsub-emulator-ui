package io.echocode.gcppubsubemulatorui.page

import geb.Page

class SubscriptionPage extends Page {

    static atCheckWaiting = true

    static url = '/project'

    static at = { title.contains("/") }

    static content = {
        subscription { $('.subscription') }
        pullMessageButton { $('.pull-message') }
        pulledMessages { $('.pulled-message') }
    }

    String convertToPath(ProjectTopicSubscriptionParams projectTopicParams) {
        "/${projectTopicParams.project}/topic/${projectTopicParams.topic}/subscription/${projectTopicParams.subscription}"
    }

    static class ProjectTopicSubscriptionParams {
        String project
        String topic
        String subscription
    }
}
