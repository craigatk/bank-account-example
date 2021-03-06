package example.bank

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import example.bank.account.BankAccountRepository
import example.bank.account.bankAccount
import example.bank.database.DataSourceConfig
import example.bank.setup.setup
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.util.*

@KtorExperimentalAPI
fun Application.module(): ModuleContext {
    val applicationConfig = environment.config

    val dataSourceConfig = DataSourceConfig.createDataSourceConfig(applicationConfig)
    val dataSource = DataSourceConfig.createDataSource(dataSourceConfig)
    DataSourceConfig.flywayMigrate(dataSource, dataSourceConfig)
    val dslContext = DataSourceConfig.createDSLContext(dataSource, dataSourceConfig)

    val bankAccountRepository = BankAccountRepository(dslContext)

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            registerModule(JavaTimeModule())
            propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
    }

    routing {
        bankAccount(bankAccountRepository)
        setup(bankAccountRepository)
    }

    return ModuleContext(
        dataSource = dataSource,
        dslContext = dslContext
    )
}
