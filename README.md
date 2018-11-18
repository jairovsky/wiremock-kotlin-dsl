
# WireMock Kotlin DSL - Use WireMock, kotlin style.

:warning: This is a work in progress. Artifacts are not yet published.

## Getting Started

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>

    <dependency>
        <groupId>com.github.tomakehurst</groupId>
        <artifactId>wiremock</artifactId>
        <version>2.19.0</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>io.github.jairovsky</groupId>
        <artifactId>wiremock-kotlin-dsl</artifactId>
        <version>2.19.0</version>
        <scope>test</scope>
    </dependency>

</dependencies>
```

### Stubbing

With the plain WireMock library, you would write something like this:
```java
stubFor(get(urlEqualTo("/my-api"))
    .willReturn(aResponse()
        .withHeader("Content-Type", "application/json")
        .withBody("{\"title\":\"The revenge of Vera Gemini\"}")))
```

With the DSL:
```kotlin
data class Song(val title: String)

stubFor {
    get {
        url equalTo "/my-api"
        willReturn {

            headers = mapOf("Content-Type" to "application/json")

            body jsonFromObject Song("The revenge of Vera Gemini")
        }
    }
}
```
