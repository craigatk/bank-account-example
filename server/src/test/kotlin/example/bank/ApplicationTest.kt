package example.bank

import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ApplicationTest : ApplicationTestCase() {

    @Test
    fun `root endpoint should return content`() {
        withTestApplication(::createTestApplication) {
            handleRequest(HttpMethod.Get, "/").apply {
                expectThat(response.status()).isEqualTo(HttpStatusCode.OK)
                expectThat(response.content).isEqualTo("HELLO WORLD!")
            }
        }
    }

    @Test
    fun `Jackson endpoint should return JSON`() {
        withTestApplication(::createTestApplication) {
            handleRequest(HttpMethod.Get, "/json/jackson").apply {
                expectThat(response.status()).isEqualTo(HttpStatusCode.OK)

                val parsedResponse = objectMapper.readValue(response.content, Map::class.java)

                expectThat(parsedResponse["hello"]).isEqualTo("world")
            }
        }
    }
}
