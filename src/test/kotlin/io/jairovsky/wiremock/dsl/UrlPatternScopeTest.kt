package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.matching.UrlPattern
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test


class UrlPatternScopeTest {

    @BeforeTest
    fun init() {
        mockkStatic(WireMock::class)
    }

    @Test
    fun `should create a pattern equivalent to WireMock#urlEqualTo`() {

        val p = mockk<UrlPattern>()
        every { WireMock.urlEqualTo(any()) } returns p

        val url = UrlPatternScope()
        url equalTo "/abcd"

        verify { WireMock.urlEqualTo("/abcd") }
        assert(url.pattern === p)
    }


    @Test
    fun `should create a pattern equivalent to WireMock#urlMatching`() {

        val p = mockk<UrlPattern>()
        every { WireMock.urlMatching(any()) } returns p

        val url = UrlPatternScope()
        url matching "/abcd"

        verify { WireMock.urlMatching("/abcd") }
        assert(url.pattern === p)
    }
}
