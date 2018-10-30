package io.jairovsky.wiremock.dsl

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.matching.UrlPattern
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class UrlPatternScope {

    var equalTo: String? = null

    val pattern: UrlPattern by UrlPatternDelegate()

}

class UrlPatternDelegate : ReadOnlyProperty<UrlPatternScope, UrlPattern> {
    override fun getValue(thisRef: UrlPatternScope, property: KProperty<*>): UrlPattern {

        require(thisRef.equalTo != null) { "You must define a url" }

        return WireMock.urlEqualTo(thisRef.equalTo)
    }
}
