package io.echocode.gcppubsubemulatorui.page

import geb.Page

class TopicPage extends Page {

    static atCheckWaiting = true

    static url = '/project'

    static at = { title.contains("/") }

    static content = {
        topics { $('.topic') }
        leads { $('.lead') }
        publishMessageForm { $('.publish-message-form') }
    }

    String convertToPath(ProjectTopicParams projectTopicParams) {
        "/${projectTopicParams.project}/topic/${projectTopicParams.topic}"
    }

    static class ProjectTopicParams {
        String project
        String topic
    }
}
