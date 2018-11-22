package io.jairovsky.wiremock.dsl

import org.junit.Test

class HeadersScopeTest {

    @Test
    fun `containing() should add pattern equivalent to WireMock#containing`() {

        val headersScope = HeadersScope()

        headersScope.apply {
            "abcd" containing "value"
        }

        assert(headersScope.headers.size == 1)
        assert(headersScope.headers["abcd"]?.value == "value")
    }
}
