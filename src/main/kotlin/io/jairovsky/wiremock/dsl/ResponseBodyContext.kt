package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.common.Json

class ResponseBodyContext {

    private var body: String? = null

    infix fun jsonFromObject(obj: Any) {

        body = Json.write(obj)
    }

    infix fun string (str: String) {
        body = str
    }

    fun createObject() = body
}
