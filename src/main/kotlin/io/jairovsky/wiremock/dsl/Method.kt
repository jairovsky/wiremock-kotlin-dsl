package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock

enum class Method {
    GET, POST, PUT, PATCH, HEAD, OPTIONS;

    operator fun invoke(initScope: StubScope.() -> Unit): MappingBuilder {

        return when(this) {
            GET -> {
                val scope = StubScope().apply(initScope)
                return WireMock.get(scope.url.pattern)
            }

            else -> throw NotImplementedError("Method ${this.name} not supported")
        }
    }
}