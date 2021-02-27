package example.bank.account

import example.bank.database.generated.enums.BankAccountType
import example.bank.database.generated.enums.TransactionType
import example.bank.database.generated.tables.daos.AccountHolderDao
import example.bank.database.generated.tables.daos.AccountTransactionDao
import example.bank.database.generated.tables.daos.BankAccountDao
import example.bank.database.generated.tables.pojos.AccountHolder
import example.bank.database.generated.tables.pojos.AccountTransaction
import example.bank.database.generated.tables.pojos.BankAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import java.math.BigDecimal

class BankAccountRepository(dslContext: DSLContext) {
    private val accountHolderDao = AccountHolderDao(dslContext.configuration())
    private val accountTransactionDao = AccountTransactionDao(dslContext.configuration())
    private val bankAccountDao = BankAccountDao(dslContext.configuration())

    suspend fun createAccountHolder(firstName: String, lastName: String): AccountHolder =
        withContext(Dispatchers.IO) {
            val accountHolder = AccountHolder(firstName = firstName, lastName = lastName)
            accountHolderDao.insert(accountHolder)

            accountHolder
        }

    suspend fun createBankAccount(accountHolderId: Long, accountType: BankAccountType): BankAccount =
        withContext(Dispatchers.IO) {
            val bankAccount = BankAccount(accountHolderId = accountHolderId, accountType = accountType)
            bankAccountDao.insert(bankAccount)

            bankAccount
        }

    suspend fun addTransaction(bankAccount: BankAccount, amount: BigDecimal, transactionType: TransactionType): AccountTransaction =
        withContext(Dispatchers.IO) {
            val transaction = AccountTransaction(
                bankAccountId = bankAccount.id,
                amount = amount,
                transactionType = transactionType
            )
            accountTransactionDao.insert(transaction)

            transaction
        }
}
