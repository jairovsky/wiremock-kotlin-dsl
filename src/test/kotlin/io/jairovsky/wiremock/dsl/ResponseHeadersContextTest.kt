package io.jairovsky.wiremock.dsl

import kotlin.test.Test

class ResponseHeadersContextTest {

    @Test
    fun `should add header`() {

        val context = ResponseHeadersContext()

        context.run {
            "abcd" withValue "1234"
        }

        context.forEach { k, v ->
            assert(k == "abcd")
            assert(v == "1234")
        }
    }
}
