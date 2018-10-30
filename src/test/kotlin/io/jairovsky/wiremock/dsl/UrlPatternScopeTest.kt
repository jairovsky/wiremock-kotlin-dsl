package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UrlPatternScopeTest {

    @Test
    fun `should validate equalTo field is not null` () {

        val scope = UrlPatternScope()

        assertFailsWith<IllegalArgumentException> {
            scope.pattern
        }
    }

    @Test
    fun `should return a UrlPattern equivalent to WireMock#urlEqualTo()` () {

        mockkStatic(WireMock::class)
        every { WireMock.urlEqualTo(any()) } returns mockk()

        val scope = UrlPatternScope()
        scope.equalTo = "/abcd"

        scope.pattern

        verify { WireMock.urlEqualTo("/abcd") }
    }
}