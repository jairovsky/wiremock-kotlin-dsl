package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock

fun stubFor(init: StubScope.() -> Unit) {

    val stubScope = StubScope().apply(init)

    stubScope.builders.forEach { WireMock.stubFor(it) }
}