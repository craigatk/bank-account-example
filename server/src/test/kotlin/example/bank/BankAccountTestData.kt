package example.bank

import example.bank.database.generated.enums.BankAccountType
import example.bank.database.generated.tables.daos.AccountHolderDao
import example.bank.database.generated.tables.daos.AccountTransactionDao
import example.bank.database.generated.tables.daos.BankAccountDao
import example.bank.database.generated.tables.pojos.AccountHolder
import example.bank.database.generated.tables.pojos.BankAccount
import org.apache.commons.lang3.RandomStringUtils
import org.jooq.DSLContext

class BankAccountTestData(dslContext: DSLContext) {
    val accountHolderDao = AccountHolderDao(dslContext.configuration())
    val bankAccountDao = BankAccountDao(dslContext.configuration())
    val accountTransactionDao = AccountTransactionDao(dslContext.configuration())

    fun createAccountHolder(userName: String = RandomStringUtils.randomAlphanumeric(16)): AccountHolder {
        val accountHolder = AccountHolder(
            userName = userName,
            firstName = "Patrick",
            lastName = "McManus"
        )
        accountHolderDao.insert(accountHolder)
        return accountHolder
    }

    fun createBankAccount(
        accountType: BankAccountType = BankAccountType.CHECKING,
        accountHolder: AccountHolder = createAccountHolder()
    ): BankAccount {
        val bankAccount = BankAccount(
            accountHolderId = accountHolder.id,
            accountType = accountType
        )
        bankAccountDao.insert(bankAccount)

        return bankAccount
    }
}
