package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder

class ResponseScope {

    private val builder =
        ResponseDefinitionBuilder()
            .withStatus(200)!!

    var headers = mapOf<String, String>()
        set(value) {
            field = value
            field.forEach { builder.withHeader(it.key, it.value) }
        }

    var status = 200
        set(value) {
            field = value
            builder.withStatus(field)
        }

    var body = ResponseBodyScope(
        builder::withBody,
        builder::withBodyFile
    )

    fun fixedDelay(fn: FixedDelay.() -> Unit) {
        val delay = FixedDelay().apply(fn)

        builder.withFixedDelay(delay.milliseconds)
    }

    fun chunkedDribbleDelay(fn: ChunkedDribbleDelay.() -> Unit) {
        val delay = ChunkedDribbleDelay().apply(fn)

        builder.withChunkedDribbleDelay(delay.numberOfChunks, delay.totalDuration)
    }

    fun logNormalRandomDelay(fn: LogNormalRandomDelay.() -> Unit) {
        val delay = LogNormalRandomDelay().apply(fn)

        builder.withLogNormalRandomDelay(delay.medianMilliseconds, delay.sigma)
    }
}

class FixedDelay {

    var milliseconds = 0
}

class ChunkedDribbleDelay {

    var numberOfChunks = 0
    var totalDuration = 0
}

class LogNormalRandomDelay {

    var medianMilliseconds: Double = 0.0
    var sigma: Double = 0.0
}
