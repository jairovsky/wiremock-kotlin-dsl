package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.http.RequestMethod

fun stubFor(fn: RequestStubCreator.() -> Unit) {

    val ctx = WireMockContext()

    RequestStubCreator(ctx).apply(fn)

    ctx.getMappings().forEach {
        WireMock.stubFor(it)
    }
}

class RequestStubCreator(val ctx: WireMockContext) {

    fun get(init: MappingBuilderContext.() -> Unit) {

        val mappingContext = MappingBuilderContext(RequestMethod.GET)

        mappingContext.init()

        ctx.addMapping(mappingContext.createObject())
    }
}

