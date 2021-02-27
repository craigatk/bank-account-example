package example.bank

import com.zaxxer.hikari.HikariDataSource
import example.bank.database.DataSourceConfig
import example.bank.database.DataSourceConfig.Companion.createDSLContext
import example.bank.database.DataSourceConfig.Companion.createDataSource
import example.bank.database.DataSourceConfig.Companion.flywayMigrate
import io.ktor.util.*
import org.jooq.DSLContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

@KtorExperimentalAPI
open class DatabaseTestCase {
    lateinit var dataSource: HikariDataSource
    lateinit var dslContext: DSLContext

    lateinit var bankAccountTestData: BankAccountTestData

    @BeforeEach
    fun setup() {
        val dataSourceConfig = DataSourceConfig(
            jdbcUrl = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5433/bankaccountdb",
            username = System.getenv("DB_USERNAME") ?: "testuser",
            password = System.getenv("DB_PASSWORD") ?: "testpass",
            schema = "public"
        )

        dataSource = createDataSource(dataSourceConfig)

        flywayMigrate(dataSource, dataSourceConfig)

        dslContext = createDSLContext(dataSource, dataSourceConfig)

        bankAccountTestData = BankAccountTestData(dslContext)
    }

    @AfterEach
    fun cleanup() {
        dataSource.close()
    }
}
