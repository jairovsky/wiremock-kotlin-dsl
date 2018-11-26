package io.jairovsky.wiremock.dsl.sandbox

import com.github.tomakehurst.wiremock.client.WireMock
import kotlin.test.Test

class UrlPatternContextTest {

    @Test(expected = UninitializedPropertyAccessException::class)
    fun `#createObject should check nullability`() {

        val context = UrlPatternContext()

        context.createObject()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `#equalTo should not allow redefinitions`() {

        val context = UrlPatternContext()

        context equalTo "/abcd"
        context equalTo "/abcd"
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `#matching should not allow redefinitions`() {

        val context = UrlPatternContext()

        context matching "/abcd"
        context matching "/abcd"
    }

    @Test
    fun `#equalTo should create a pattern equivalent to WireMock#urlEqualTo`() {

        val context = UrlPatternContext()

        context equalTo "/abcd"

        assert(context.createObject() == WireMock.urlEqualTo("/abcd"))
    }

    @Test
    fun `#matching should create a pattern equivalent to WireMock#urlMatching`() {

        val context = UrlPatternContext()

        context matching "/abcd"

        assert(context.createObject() == WireMock.urlMatching("/abcd"))
    }
}
