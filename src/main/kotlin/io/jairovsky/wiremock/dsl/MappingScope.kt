package io.jairovsky.wiremock.dsl

class MappingScope {

    val url = UrlPatternScope()



    fun willReturn (init: ResponseScope.() -> Unit) {

    }

}