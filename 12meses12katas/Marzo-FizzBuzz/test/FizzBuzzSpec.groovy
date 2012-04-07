import spock.lang.Specification
import spock.lang.Unroll

class FizzBuzzSpec extends Specification {

    static final FIZZ_NUMBERS = (3..100).step(3) + (0..100).findAll { it.toString().contains('3') }
    static final BUZZ_NUMBERS = (5..100).step(5) + (0..100).findAll { it.toString().contains('5') }

    def fizzBuzz

    def setup() {
        fizzBuzz = new FizzBuzz()
    }

    @Unroll
    def "numbers divisible by 3 or with a 3 in it's value are Fizz"() {
        expect:
        fizzBuzz.say(number) == 'Fizz'

        where:
        number << FIZZ_NUMBERS - BUZZ_NUMBERS
    }

    @Unroll
    def "numbers divisible by 5 or with a 5 in it's value are Buzz"() {
        expect:
        fizzBuzz.say(number) == 'Buzz'

        where:
        number << BUZZ_NUMBERS - FIZZ_NUMBERS
    }

    @Unroll
    def 'numbers that are Fizz and Buzz at the same time'() {
        expect:
        fizzBuzz.say(number) == 'FizzBuzz'
        
        where:
        number << (FIZZ_NUMBERS.intersect(BUZZ_NUMBERS) as Set)
    }
}
