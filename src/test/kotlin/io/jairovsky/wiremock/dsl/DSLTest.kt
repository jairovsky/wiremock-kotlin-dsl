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
    fun init () {
        mockkStatic(WireMock::class)
        every { WireMock.stubFor(any()) } returns mockk()
    }

    @Test
    fun `experimenting` () {

//        WireMock.stubFor(
//                WireMock.get(WireMock.urlEqualTo(""))
//
//                .willReturn())

        stubFor {
            get {
                url equalTo "/my-api"
            }

            patch {
                url matching "/my-api-2"
            }
        }

        verify(exactly = 2) { WireMock.stubFor(any()) }

    }

}
