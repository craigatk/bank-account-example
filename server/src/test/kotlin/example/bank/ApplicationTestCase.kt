package example.bank

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.zaxxer.hikari.HikariDataSource
import example.bank.database.generated.tables.daos.AccountHolderDao
import example.bank.database.generated.tables.daos.AccountTransactionDao
import example.bank.database.generated.tables.daos.BankAccountDao
import io.ktor.application.*
import io.ktor.config.*
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach

open class ApplicationTestCase {
    val objectMapper: ObjectMapper = ObjectMapper()
        .registerKotlinModule()
        .registerModule(JavaTimeModule())
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    lateinit var dataSource: HikariDataSource
    lateinit var dslContext: DSLContext

    lateinit var accountHolderDao: AccountHolderDao
    lateinit var bankAccountDao: BankAccountDao
    lateinit var accountTransactionDao: AccountTransactionDao

    fun createTestApplication(application: Application) {

        (application.environment.config as MapApplicationConfig).apply {
            // Set here the properties
            put("ktor.datasource.username", System.getenv("DB_USERNAME") ?: "testuser")
            put("ktor.datasource.password", System.getenv("DB_PASSWORD") ?: "testpass")
            put("ktor.datasource.jdbcUrl", System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5433/bankaccountdb")
            put("ktor.datasource.schema", "public")
        }

        val appContext = application.module()
        dataSource = appContext.dataSource
        dslContext = appContext.dslContext

        accountHolderDao = AccountHolderDao(dslContext.configuration())
        bankAccountDao = BankAccountDao(dslContext.configuration())
        accountTransactionDao = AccountTransactionDao(dslContext.configuration())
    }

    @AfterEach
    fun closeDataSource() {
        dataSource.close()
    }
}
