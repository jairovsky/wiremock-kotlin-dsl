package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

class DSLTest {

    @Before
    fun init() {
        mockkStatic(WireMock::class)
        WireMock.removeAllMappings()
        //every { WireMock.stubFor(any()) } returns mockk()
    }

    @Test
    fun `experimenting`() {

        stubFor {
            get {
                url equalTo "/my-api"
                willReturn {

                    headers = mapOf(
                        "X-Request-Id" to "abcdbebriqwey",
                        "Content-Type" to "application/json")

                    body jsonFromObject Song("The revenge of Vera Gemini")

                }
            }

            patch {
                url matching "/my-api-2"
                willReturn {
                    body file "sample.json"
                }
            }
        }

        //verify(exactly = 2) { WireMock.stubFor(any()) }


    }
}

data class Song(
    val title: String
)
