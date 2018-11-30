package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.junit.Test

class DSLTest {

    @Rule @JvmField val wireMockRule = WireMockRule(WireMockConfiguration.options().notifier(ConsoleNotifier(true)))

    @Test
    fun `experimenting`() {

        stubFor {
            get {
                url equalTo "/my-api"
                willReturn {

                    headers {
                        "Content-Type" withValue "application/json"
                        "X-Request-Id" withValue "f87ce6b0-f1bc-11e8-b9ee-273fd06674b2"
                    }

                    body jsonFromObject Song("The revenge of Vera Gemini")
                }
            }

           /* patch {
                url matching "/my-api-2"
                willReturn {
                    body file "sample.json"

                    fixedDelay {
                        milliseconds = 1000
                    }

                    chunkedDribbleDelay {
                        numberOfChunks = 5
                        totalDuration = 1000
                    }

                    logNormalRandomDelay {
                        medianMilliseconds = 90.0
                        sigma = 0.1
                    }

                    withFault {
                        type = Fault.MALFORMED_RESPONSE_CHUNK
                    }
                }
            }*/
        }

        //verify(exactly = 2) { WireMock.stubFor(any()) }
    }
}

data class Song(
    val title: String
)
