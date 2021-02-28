package example.bank

import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext

data class ModuleContext(
    val dataSource: HikariDataSource,
    val dslContext: DSLContext
)
