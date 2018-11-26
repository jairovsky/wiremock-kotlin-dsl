package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder

class ResponseDefinitionContext {

    private val headersContext = ResponseHeadersContext()

    fun headers(init: ResponseHeadersContext.() -> Unit) {

        headersContext.init()
    }

    fun createObject(): ResponseDefinitionBuilder {

        val responseDefinition = ResponseDefinitionBuilder.responseDefinition()

        headersContext.forEach { k, v -> responseDefinition.withHeader(k, v) }

        return responseDefinition
    }
}
