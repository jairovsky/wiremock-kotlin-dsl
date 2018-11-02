package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock

class StubScope {

    val builders = mutableListOf<MappingBuilder>()

    fun get(init: MappingScope.() -> Unit) {

        addNewMapping("GET", init)
    }

    fun patch(init: MappingScope.() -> Unit) {

        addNewMapping("PATCH", init)
    }

    private fun addNewMapping(method: String, init: MappingScope.() -> Unit) {

        val scope = MappingScope()
        scope.init()

        builders.add(buildRealWireMockMapping(method, scope))
    }

    private fun buildRealWireMockMapping(method: String,
                                         scope: MappingScope): MappingBuilder {

        return WireMock
            .request(method, scope.url.pattern)
            .willReturn(scope.willReturn.builder)
    }
}
