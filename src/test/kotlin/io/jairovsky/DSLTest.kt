package io.jairovsky

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import io.jairovsky.wiremock.dsl.stubFor
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class DSLTest {

    @Before
    fun init () {
        mockkStatic(WireMock::class)
        every { WireMock.stubFor(any()) } returns mockk()
    }

    @After
    fun tear () {
        unmockkStatic(WireMock::class)
    }

    @Test
    fun `stubFor() should call WireMock stubFor` () {


    }

    @Test
    fun `stubFor should accept multiple mappings` () {


    }

}
