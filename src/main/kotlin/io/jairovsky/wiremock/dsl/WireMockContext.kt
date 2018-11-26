package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.MappingBuilder

class WireMockContext {

    private val mappings = mutableListOf<MappingBuilder>()

    fun addMapping(m: MappingBuilder) {
        mappings.add(m)
    }

    fun getMappings() = mappings
}
