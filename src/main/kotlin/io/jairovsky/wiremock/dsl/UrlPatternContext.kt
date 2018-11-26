package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.matching.UrlPattern

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
