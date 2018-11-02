package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder

class ResponseScope {

    val builder = ResponseDefinitionBuilder()

    var headers = mapOf<String, String>()
        set(value) {
            field = value
            field.forEach { builder.withHeader(it.key, it.value) }
        }

}
