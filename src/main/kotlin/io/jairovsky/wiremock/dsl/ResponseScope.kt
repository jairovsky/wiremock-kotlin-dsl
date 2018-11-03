package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder

class ResponseScope {

    val builder =
        ResponseDefinitionBuilder()
            .withStatus(200)!!

    var headers = mapOf<String, String>()
        set(value) {
            field = value
            field.forEach { builder.withHeader(it.key, it.value) }
        }

    var status = 200
        set(value) {
            field = value
            builder.withStatus(field)
        }

    var body = ResponseBodyScope(
        builder::withBody,
        builder::withBodyFile
    )
}
