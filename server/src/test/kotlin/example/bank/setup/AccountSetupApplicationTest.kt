package example.bank.setup

import example.bank.ApplicationTestCase
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.util.*
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNullOrBlank
import kotlin.test.assertNotNull

@KtorExperimentalAPI
class AccountSetupApplicationTest : ApplicationTestCase() {
    @Test
    fun `should generate account holder and bank account`() {
        withTestApplication(::createTestApplication) {
            handleRequest(HttpMethod.Post, "/account/generate").apply {
                expectThat(response.status()).isEqualTo(HttpStatusCode.OK)

                val accountSetupResponse = objectMapper.readValue(response.content, AccountSetup::class.java)

                expectThat(accountSetupResponse.accountId).not().isNullOrBlank()

                val bankAccount = bankAccountTestData.bankAccountDao.fetchOneById(accountSetupResponse.accountId.toLong())
                assertNotNull(bankAccount)

                val accountHolderId = bankAccount.accountHolderId
                assertNotNull(accountHolderId)

                val accountHolder = bankAccountTestData.accountHolderDao.fetchOneById(accountHolderId)
                assertNotNull(accountHolder)
            }
        }
    }
}
