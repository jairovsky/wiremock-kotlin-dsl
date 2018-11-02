package io.jairovsky.wiremock.dsl

class MappingScope {

    val url = UrlPatternScope()
    var willReturn = ResponseScope()

    fun willReturn(fn: ResponseScope.() -> Unit) {
        this.willReturn = ResponseScope().apply(fn)
    }
}