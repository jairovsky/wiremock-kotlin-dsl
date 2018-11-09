package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.common.Json
import com.github.tomakehurst.wiremock.http.Fault
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResponseScopeTest {

    private lateinit var responseScope: ResponseScope
    private val builder: ResponseDefinitionBuilder = mockk(relaxed = true)

    @Before
    fun init() {
        mockkConstructor(ResponseDefinitionBuilder::class)

        every {
            anyConstructed<ResponseDefinitionBuilder>().withStatus(any())
        } returns builder

        responseScope = ResponseScope()

        mockkStatic(Json::class)
    }

    @After
    fun tear() {
        unmockkConstructor(ResponseDefinitionBuilder::class)
        unmockkStatic(Json::class)
    }

    @Test
    fun `setting the status should trigger builder#withStatus` () {

        responseScope.status = 302

        verify { builder.withStatus(302) }
    }

    @Test
    fun `setting headers should trigger builder#withHeader for each entry` () {

        responseScope.headers = mapOf(
            "Accept" to "application/json",
            "Content-Type" to "application/xml"
        )

        verify { builder.withHeader("Accept", "application/json") }
        verify { builder.withHeader("Content-Type", "application/xml") }
    }

    @Test
    fun `setting fixedDelay should trigger builder#withFixedDelay` () {

        responseScope.fixedDelay { milliseconds = 1543 }

        verify { builder.withFixedDelay(1543) }
    }

    @Test
    fun `setting chunkedDribbleDelay should trigger builder#withChunkedDribbleDelay` () {

        responseScope.chunkedDribbleDelay {
            numberOfChunks = 5
            totalDuration = 1234
        }

        verify { builder.withChunkedDribbleDelay(5, 1234) }
    }

    @Test
    fun `setting logNormalRandomDelay should trigger builder#withLogNormalRandomDelay` () {

        responseScope.logNormalRandomDelay {
            medianMilliseconds = 90.0
            sigma = 0.2
        }

        verify { builder.withLogNormalRandomDelay(90.0, 0.2) }
    }

    @Test
    fun `setting fault should trigger builder#withFault` () {

        responseScope.withFault { type = Fault.CONNECTION_RESET_BY_PEER }

        verify { builder.withFault(Fault.CONNECTION_RESET_BY_PEER) }
    }

    @Test
    fun `setting body string should trigger builder#withBody` () {

        responseScope.body string "abcd"

        verify { builder.withBody("abcd") }
    }

    @Test
    fun `setting body file should trigger builder#withBodyFile` () {

        responseScope.body file "abcd.json"

        verify { builder.withBodyFile("abcd.json") }
    }

    @Test
    fun `setting body jsonFromObject should builder#withBody with serialized object` () {

        data class Song(val title: String)


        every { Json.write(any<Song>()) } returns "this would actually be a json"

        responseScope.body jsonFromObject Song("The revenge of Vera Gemini")

        verify { builder.withBody("this would actually be a json") }

    }
}
