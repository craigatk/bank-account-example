package example.account

import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ApplicationTest {

    @Test
    fun `root endpoint should return content`() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                expectThat(response.status()).isEqualTo(HttpStatusCode.OK)
                expectThat(response.content).isEqualTo("HELLO WORLD!")
            }
        }
    }
}