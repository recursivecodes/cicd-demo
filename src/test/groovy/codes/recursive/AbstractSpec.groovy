package codes.recursive

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.runtime.config.SchemaGenerate
import org.testcontainers.containers.OracleContainer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class AbstractSpec extends Specification {
    @Shared
    @AutoCleanup
    static OracleContainer oracleContainer =
            new OracleContainer("gvenzl/oracle-xe:18-slim")
                    .withEnv("ORACLE_PWD", "Str0ngPassw0rd")
                    .withStartupTimeoutSeconds(900)
                    .withConnectTimeoutSeconds(900)
                    .withPassword("Str0ngPassw0rd")
    @Shared
    @AutoCleanup
    static ApplicationContext context
    static  {
        System.setProperty("oracle.jdbc.fanEnabled", "false")
        oracleContainer.start()
        context = ApplicationContext.run(
                [
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
