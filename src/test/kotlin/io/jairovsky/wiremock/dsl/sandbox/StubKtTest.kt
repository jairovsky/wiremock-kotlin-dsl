package io.jairovsky.wiremock.dsl.sandbox

import kotlin.test.Test

class StubKtTest {

    @Test
    fun `stubFor should expose StubCreator`() {

        stubFor {
            get {
                url equalTo "/huehue"
                url equalTo "/huehue"
            }
        }
    }
}
