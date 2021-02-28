package example.bank.account

import example.bank.DatabaseTestCase
import example.bank.database.generated.enums.BankAccountType
import example.bank.database.generated.enums.TransactionType
import io.ktor.util.*
import kotlinx.coroutines.runBlocking
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.any
import strikt.assertions.hasSize
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

    @Test
    fun `should fetch bank account by account holder user name`() {
        val bankAccountRepository = BankAccountRepository(dslContext)

        val userName = RandomStringUtils.randomAlphanumeric(16)
        val accountHolderDB = bankAccountTestData.createAccountHolder(userName)
        val accountDB = bankAccountTestData.createBankAccount(BankAccountType.CHECKING, accountHolderDB)

        val bankAccounts = runBlocking { bankAccountRepository.fetchBankAccounts(userName) }

        expectThat(bankAccounts).hasSize(1).and {
            any {
                get { id }.isEqualTo(accountDB.id)
                get { accountType }.isEqualTo(BankAccountType.CHECKING)
            }
        }
    }
}
