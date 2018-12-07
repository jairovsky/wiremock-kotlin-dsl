package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class ResponseDefinitionContextTest {

    private lateinit var builder: ResponseDefinitionBuilder

    @BeforeTest
    fun setup() {

        mockkStatic(ResponseDefinitionBuilder::class)
        builder = mockk(relaxed = true)
        every { ResponseDefinitionBuilder.responseDefinition() } returns builder
    }

    @Test
    fun `should use default status 200`() {

        ResponseDefinitionContext().createObject()

        verify { builder.withStatus(200) }
    }

    @Test
    fun `should use specified status`() {

        val context = ResponseDefinitionContext()

        context.run {
            status = 302
        }

        context.createObject()

        verify { builder.withStatus(302) }
    }

    @Test
    fun `should set headers`() {

        val context = ResponseDefinitionContext()

        context.run {
            headers {
                "Content-Type" withValue "app/yaml"
                "X-Req-Id" withValue "abcd"
            }
        }

        context.createObject()

        verify {
            builder.withHeader("Content-Type", "app/yaml")
            builder.withHeader("X-Req-Id", "abcd")
        }
    }

    @Test
    fun `should return builder`() {

        val result = ResponseDefinitionContext().createObject()

        assert(result === builder)
    }
}
