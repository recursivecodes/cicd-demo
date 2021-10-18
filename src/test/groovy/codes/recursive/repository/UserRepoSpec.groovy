package codes.recursive.repository

import codes.recursive.AbstractSpec
import codes.recursive.domain.User
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.testcontainers.spock.Testcontainers

@MicronautTest
@Testcontainers
class UserRepoSpec extends AbstractSpec {
    def "Can create a user"() {
        when:
        UserRepository userRepository = context.getBean(UserRepository)
        def user = new User("Todd", "Sharp", 43, "todd.sharp@oracle.com")
        userRepository.save(user)
        then:
        userRepository.findByFirstName("Todd").size() > 0
    }
}