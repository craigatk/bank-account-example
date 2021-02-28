package example.bank.setup

import example.bank.account.BankAccountRepository
import example.bank.database.generated.enums.BankAccountType
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.apache.commons.lang3.RandomStringUtils

fun Route.setup(bankAccountRepository: BankAccountRepository) {
    post("/account/generate") {
        val accountHolder = bankAccountRepository.createAccountHolder(
            userName = RandomStringUtils.randomAlphanumeric(16),
            firstName = "John",
            lastName = "Doe"
        )
        val accountHolderId = accountHolder.id

        accountHolderId?.let {
            val bankAccount = bankAccountRepository.createBankAccount(accountHolderId, BankAccountType.CHECKING)
            val bankAccountId = bankAccount.id

            bankAccountId?.let {
                call.respond(HttpStatusCode.OK, AccountSetup(accountId = bankAccount.id!!.toString()))
            } ?: call.respond(HttpStatusCode.NotFound)
        } ?: call.respond(HttpStatusCode.NotFound)
    }
}
