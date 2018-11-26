package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.http.RequestMethod

class MappingBuilderContext(private val method: RequestMethod) {

    val url = UrlPatternContext()
    private val responseDefinitionContext = ResponseDefinitionContext()

    fun willReturn(init: ResponseDefinitionContext.() -> Unit) {

        responseDefinitionContext.init()
    }

    fun createObject(): MappingBuilder {

        return WireMock.request(method.name, url.createObject())
            .willReturn(responseDefinitionContext.createObject())
    }
}
