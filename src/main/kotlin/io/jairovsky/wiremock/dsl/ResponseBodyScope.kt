package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.common.Json

class ResponseBodyScope(val fn: (bodyString: String) -> Unit) {

    infix fun jsonFromObject(obj: Any) = fn(Json.write(obj))
    infix fun string(string: String) = fn(string)
}
