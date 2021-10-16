package codes.recursive

import io.micronaut.test.extensions.spock.annotation.MicronautTest

@MicronautTest
class HelloWorldSpec extends AbstractSpec {

    def "test hello world"() {
        def foo = 'bar'
        when:
        foo == 'bar'
        then:
        foo.reverse() == 'rab'
    }

    def "test failure"() {
        when:
        true == true
        then:
        false == true
    }

}
