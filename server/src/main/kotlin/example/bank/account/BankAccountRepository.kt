package example.bank.account

import example.bank.database.generated.tables.daos.AccountHolderDao
import example.bank.database.generated.tables.daos.BankAccountDao
import example.bank.database.generated.tables.pojos.AccountHolder
import example.bank.database.generated.tables.pojos.BankAccount
import org.jooq.DSLContext

class BankAccountRepository(dslContext: DSLContext) {
    private val accountHolderDao = AccountHolderDao(dslContext.configuration())
    private val bankAccountDao = BankAccountDao(dslContext.configuration())

    fun createAccountHolder(firstName: String, lastName: String): AccountHolder {
        val accountHolder = AccountHolder(firstName = firstName, lastName = lastName)
        accountHolderDao.insert(accountHolder)

        return accountHolder
    }

    fun createBankAccount(accountHolderId: Long, accountType: BankAccountType): BankAccount {
        val bankAccount = BankAccount(accountHolderId = accountHolderId, accountType = accountType.name)
        bankAccountDao.insert(bankAccount)

        return bankAccount
    }
}
