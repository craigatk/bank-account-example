package example.bank.account

import com.fasterxml.jackson.module.kotlin.readValue
import example.bank.ApplicationTestCase
import example.bank.database.generated.enums.BankAccountType
import example.bank.database.generated.tables.pojos.BankAccount
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.util.*
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.all
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import kotlin.test.assertNotNull

@KtorExperimentalAPI
class BankAccountApplicationTest : ApplicationTestCase() {

    @Test
    fun `should list user bank accounts`() {
        val userName = RandomStringUtils.randomAlphanumeric(16)

        withTestApplication(::createTestApplication) {
            handleRequest(HttpMethod.Get, "/accounts/$userName") {
                val accountHolder = bankAccountTestData.createAccountHolder(userName)
                bankAccountTestData.createBankAccount(BankAccountType.CHECKING, accountHolder)
            }.apply {
                expectThat(response.status()).isEqualTo(HttpStatusCode.OK)

                val responseBody = response.content
                assertNotNull(responseBody)

                val bankAccounts: List<BankAccount> = objectMapper.readValue(responseBody)
                expectThat(bankAccounts).hasSize(1).all {
                    get { accountType }.isEqualTo(BankAccountType.CHECKING)
                }
            }
        }
    }

    @Test
    fun `when no accounts for username should return 404`() {
        val userName = RandomStringUtils.randomAlphanumeric(16)

        withTestApplication(::createTestApplication) {
            handleRequest(HttpMethod.Get, "/accounts/$userName") {
                bankAccountTestData.createAccountHolder(userName)
            }.apply {
                expectThat(response.status()).isEqualTo(HttpStatusCode.NotFound)
            }
        }
    }
}
