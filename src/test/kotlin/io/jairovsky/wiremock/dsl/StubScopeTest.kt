package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import io.mockk.*
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

class StubScopeTest {

    companion object {
        @BeforeClass @JvmStatic
        fun init() {
            mockkStatic(WireMock::class)
            every { WireMock.request(any(), any()) } returns mockk(relaxed = true)
        }

        @AfterClass @JvmStatic
        fun tear() {
            unmockkStatic(WireMock::class)
        }
    }

    @Test
    fun `get() should generate a GET mapping`() {

        val stubScope = StubScope()
        stubScope.get { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("GET", any()) }
    }

    @Test
    fun `post() should generate a POST mapping`() {

        val stubScope = StubScope()
        stubScope.post { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("POST", any()) }
    }

    @Test
    fun `put() should generate a PUT mapping`() {

        val stubScope = StubScope()
        stubScope.put { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("PUT", any()) }
    }

    @Test
    fun `delete() should generate a DELETE mapping`() {

        val stubScope = StubScope()
        stubScope.delete { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("DELETE", any()) }
    }

    @Test
    fun `patch() should generate a PATCH mapping`() {

        val stubScope = StubScope()
        stubScope.patch { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("PATCH", any()) }
    }

    @Test
    fun `options() should generate an OPTIONS mapping`() {

        val stubScope = StubScope()
        stubScope.options { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("OPTIONS", any()) }
    }

    @Test
    fun `head() should generate a HEAD mapping`() {

        val stubScope = StubScope()
        stubScope.head { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("HEAD", any()) }
    }

    @Test
    fun `trace() should generate a TRACE mapping`() {

        val stubScope = StubScope()
        stubScope.trace { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("TRACE", any()) }
    }

    @Test
    fun `any() should generate an ANY mapping`() {

        val stubScope = StubScope()
        stubScope.any { url matching "/" }

        assert(stubScope.builders.size == 1)

        verify { WireMock.request("ANY", any()) }
    }

    @Test
    fun `should pass willReturn definitions`() {

        val builder = mockk<MappingBuilder>(relaxed = true)
        every { WireMock.request(any(), any()) } returns builder

        val stubScope = StubScope()
        stubScope.any { url matching "/" }

        verify { builder.willReturn(any()) }
    }
}
