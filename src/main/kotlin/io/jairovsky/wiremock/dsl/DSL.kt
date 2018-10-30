package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping

fun stubFor(fn: () -> MappingBuilder): StubMapping {
    return WireMock.stubFor(fn())
}