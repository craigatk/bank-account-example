package example.account

import com.zaxxer.hikari.HikariDataSource
import org.jooq.DSLContext

data class AppContext(
    val dataSource: HikariDataSource,
    val dslContext: DSLContext
)
