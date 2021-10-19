package codes.recursive

import io.micronaut.context.ApplicationContext
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class AbstractSpec extends Specification {
    @Shared
    @AutoCleanup
    static ApplicationContext context

    static  {
        context = ApplicationContext.run()
    }

}
