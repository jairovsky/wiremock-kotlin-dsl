package io.jairovsky.wiremock.dsl.sandbox

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.http.RequestMethod
import com.github.tomakehurst.wiremock.matching.UrlPattern

fun stubFor(fn: RequestStubCreator.() -> Unit) {

    val ctx = WireMockContext()

    RequestStubCreator(ctx).apply(fn)

    ctx.getMappings().forEach {
        WireMock.stubFor(it)
    }
}

class WireMockContext {

    private val mappings = mutableListOf<MappingBuilder>()

    fun addMapping(m: MappingBuilder) {
        mappings.add(m)
    }

    fun getMappings() = mappings
}

class RequestStubCreator(val ctx: WireMockContext) {

    fun get(init: MappingBuilderContext.() -> Unit) {

        val mappingContext = MappingBuilderContext(RequestMethod.GET)

        mappingContext.init()



        ctx.addMapping(mappingContext.createObject())
    }
}

class MappingBuilderContext(private val method: RequestMethod) {

    fun createObject(): MappingBuilder {

        return WireMock.request(method.name, url.createObject())
    }

    val url = UrlPatternContext()
}

class UrlPatternContext {

    private var _pattern: UrlPattern? = null

    infix fun equalTo(url: String) {

        assertUndefined()
        _pattern = WireMock.urlEqualTo(url)
    }

    infix fun matching(strPattern: String) {

        assertUndefined()
        _pattern = WireMock.urlMatching(strPattern)
    }

    fun createObject(): UrlPattern {

        assertDefined()

        return _pattern as UrlPattern
    }

    private fun assertUndefined() {

        if (_pattern != null) {
            throw UnsupportedOperationException("Property 'url' was already defined")
        }
    }

    private fun assertDefined() {

        if (_pattern == null) {

            throw UninitializedPropertyAccessException("""
                    Url pattern not defined.

                    You can define it using
                        url equalTo "/something"
                    or
                        url matching "/something"
                """.trimIndent())
        }
    }
}
