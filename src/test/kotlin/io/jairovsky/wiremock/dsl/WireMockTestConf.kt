package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration

object WireMockTestConf : WireMockConfiguration() {

    init {
        notifier(ConsoleNotifier(true))
    }
}
