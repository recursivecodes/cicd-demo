package codes.recursive

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.runtime.config.SchemaGenerate
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.OracleContainer
import org.testcontainers.containers.wait.strategy.Wait
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class AbstractSpec extends Specification {
    @Shared
    @AutoCleanup
    static ApplicationContext context
    static {
        OracleContainer oracleContainer = new OracleContainer("gvenzl/oracle-xe:18-slim")
        oracleContainer.start()

        System.setProperty("oracle.jdbc.fanEnabled", "false")

        context = ApplicationContext.run(
                [
                        "datasources.default.connection-factory-class-name": "oracle.jdbc.pool.OracleDataSource",
                        "datasources.default.driver-class-name": "oracle.jdbc.driver.OracleDriver",
                        "datasources.default.url": oracleContainer.getJdbcUrl(),
                        "datasources.default.username": oracleContainer.getUsername(),
                        "datasources.default.password": oracleContainer.getPassword(),
                        "datasources.default.schema-generate": SchemaGenerate.NONE,
                        "datasources.default.dialect": Dialect.ORACLE
                ],
                Environment.TEST
        )
    }
}
