package io.jairovsky.wiremock.dsl

class MappingScope {

    val url = UrlPatternScope()
    var willReturn = ResponseScope()
    var headers = HeadersScope()

    fun willReturn(fn: ResponseScope.() -> Unit) {
        this.willReturn = ResponseScope().apply(fn)
    }

    fun headers(fn: HeadersScope.() -> Unit) {
        this.headers = HeadersScope().apply(fn)
    }
}
