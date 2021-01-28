package example.bank.setup

import example.bank.account.BankAccountRepository
import example.bank.account.BankAccountType
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.setup(bankAccountRepository: BankAccountRepository) {
    post("/account/generate") {
        val accountHolder = bankAccountRepository.createAccountHolder("John", "Doe")
        val bankAccount = bankAccountRepository.createBankAccount(accountHolder.id!!, BankAccountType.CHECKING)

        call.respond(HttpStatusCode.OK, AccountSetup(accountId = bankAccount.id!!.toString()))
    }
}
