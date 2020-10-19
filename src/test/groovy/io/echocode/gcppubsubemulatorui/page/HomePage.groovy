package io.echocode.gcppubsubemulatorui.page

import geb.Page

class HomePage extends Page {

    static atCheckWaiting = true

    static url = '/'

    static at = { title == 'Pub/Sub UI' }

    static content = {
        topics { $('.topic') }
        subscriptions { $('.subscription') }
    }
}
