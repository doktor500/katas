import spock.lang.Unroll
import spock.lang.Specification

class PrimeFactorsSpec extends Specification {

    def setupSpec()
    {
        Integer.metaClass.getPrimeFactors = { ->
            (1..delegate).inject([]) { list, it -> (delegate % it == 0) ? list << it: list }
        }
    }

    @Unroll
    def "return the prime factors for a given number"() {
        expect:
        number.primeFactors == expectedPrimeFactors

        where:
        number  | expectedPrimeFactors
        1       | [1]
        2       | [1, 2]
        3       | [1, 3]
        4       | [1, 2, 4]
        5       | [1, 5]
        6       | [1, 2, 3, 6]
        7       | [1, 7]
        8       | [1, 2, 4, 8]
        9       | [1, 3, 9]
        10      | [1, 2, 5, 10]
    }
}