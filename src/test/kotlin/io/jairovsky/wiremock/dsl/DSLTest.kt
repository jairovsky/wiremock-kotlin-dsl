package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DSLTest {

    @Before
    fun init() {
        mockkStatic(WireMock::class)
        every { WireMock.stubFor(any()) } returns mockk()
    }

    @Test
    fun `experimenting`() {

        stubFor {
            get {
                url equalTo "/my-api"
                willReturn {
                    status = 200
                    headers = mapOf(
                        "X-Request-Id" to "abcdbebriqwey"
                    )
                }
            }

            patch {
                url matching "/my-api-2"
            }
        }

        verify(exactly = 2) { WireMock.stubFor(any()) }

    }

}
