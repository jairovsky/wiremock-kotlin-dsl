package io.jairovsky.wiremock.dsl

import kotlin.test.Test

class ResponseBodyContextTest {

    @Test
    fun `#string should just assign a string`() {

        val context = ResponseBodyContext()

        context string "abcd"

        assert("abcd" == context.createObject())
    }

    @Test
    fun `#jsonFromObject should serialize a data class to json`() {

        val context = ResponseBodyContext()

        context jsonFromObject Person("John Doe")

        assert("""{
            |  "name" : "John Doe"
            |}""".trimMargin() == context.createObject())
    }
}

data class Person(val name: String)
