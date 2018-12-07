package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder

class ResponseDefinitionContext {

    var status = 200
    private val headersContext = ResponseHeadersContext()
    val body = ResponseBodyContext()

    fun headers(init: ResponseHeadersContext.() -> Unit) =
        headersContext.apply(init)

    fun createObject(): ResponseDefinitionBuilder {

        val responseDefinition =
            ResponseDefinitionBuilder.responseDefinition()

        responseDefinition
            .withStatus(status)

        for ((k, v) in headersContext)
            responseDefinition.withHeader(k, v)

        responseDefinition
            .withBody(body.createObject())

        return responseDefinition
    }
}
