package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.matching.StringValuePattern

class HeadersScope {

    val headers = mutableMapOf<String, StringValuePattern>()

    infix fun String.containing(pattern: String) {

        headers[this] = WireMock.containing(pattern)
    }

    infix fun String.matching(pattern: String) {

        headers[this] = WireMock.matching(pattern)
    }
}
