package io.jairovsky.wiremock.dsl.sandbox

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.matching.UrlPattern

fun stubFor(fn: StubCreator.() -> Unit) {

    StubCreator(WireMockContext()).apply(fn)
}

class WireMockContext {

    private val mappings = mutableListOf<MappingBuilder>()

    fun addMapping(m: MappingBuilder) {
        mappings.add(m)
    }
}

class StubCreator(val ctx: WireMockContext) {

    fun get(init: MappingBuilderContext.() -> Unit) {

        val mappingContext = MappingBuilderContext()

        mappingContext.init()

        //ctx.addMapping()
    }
}

class MappingBuilderContext {

    val url = UrlPatternCreator()
    private val assertions = mutableSetOf<String>()

    init {
        url.onPattern {

            if (assertions.contains("URL")) {
                throw RepeatedOperation()
            }

            assertions.add("URL")
        }
    }

}

class UrlPatternCreator {

    private val listeners = mutableListOf<(UrlPattern) -> Unit>()

    infix fun equalTo(url: String) {

        val pattern = WireMock.urlEqualTo(url)

        listeners.forEach { it(pattern) }
    }

    infix fun matching(strPattern: String) {

        val pattern = WireMock.urlMatching(strPattern)

        listeners.forEach { it(pattern) }
    }

    fun onPattern(function: (UrlPattern) -> Unit) {

        listeners.add(function)
    }
}


class RepeatedOperation() :
    Exception("Duplicate operation in the same context. Are you trying to define a property more than once?")
