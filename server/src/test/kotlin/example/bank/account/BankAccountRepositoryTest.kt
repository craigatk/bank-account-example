package example.bank.account

import example.bank.DatabaseTestCase
import example.bank.database.generated.enums.TransactionType
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigDecimal

@KtorExperimentalAPI
class BankAccountRepositoryTest : DatabaseTestCase() {
    @Test
    fun `should add account transaction`() {
        val bankAccountRepository = BankAccountRepository(dslContext)

        val account = bankAccountTestData.createBankAccount()

        val accountTransaction = runBlocking { bankAccountRepository.addTransaction(account, BigDecimal("100.00"), TransactionType.DEPOSIT) }

        expectThat(accountTransaction) {
            get { amount }.isEqualTo(BigDecimal("100.00"))
            get { transactionType }.isEqualTo(TransactionType.DEPOSIT)
        }
    }
}
