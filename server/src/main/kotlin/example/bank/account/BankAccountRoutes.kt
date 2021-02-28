package example.bank.account

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*

fun Route.bankAccount(bankAccountRepository: BankAccountRepository) {
    get("/accounts/{userName}") {
        val userName = call.parameters.getOrFail("userName")

        val accounts = bankAccountRepository.fetchBankAccounts(userName)

        if (accounts.isNotEmpty()) {
            call.respond(HttpStatusCode.OK, accounts)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}
