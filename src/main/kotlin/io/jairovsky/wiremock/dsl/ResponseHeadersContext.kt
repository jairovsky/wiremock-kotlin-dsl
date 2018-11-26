package io.jairovsky.wiremock.dsl

class ResponseHeadersContext {

    private val headers = mutableMapOf<String, String>()

    infix fun String.withValue(value: String) {

        headers.putIfAbsent(this, value)
    }

    fun forEach (fn: (String, String) -> Unit) {

        headers.forEach {fn(it.key, it.value)}
    }
}
