import spock.lang.Specification
import spock.lang.Unroll

class StringCalculatorSpec extends Specification {

    //Real collaborator is used instead of mock, because in these case, it's better an end to end test of the algorithm
    def stringCalculator = new StringCalculator(stringCalculatorParser: new StringCalculatorParser())
    static final DEL = ','

    def 'returns zero for an empty string'() {
        expect:
        stringCalculator.add('') == 0
    }

    @Unroll
    def 'one number string returns the number value'() {
        expect:
        stringCalculator.add(number) == number.toInteger()

        where:
        number << ['1', '2', '33']
    }

    @Unroll
    def 'returns the sum of two numbers'() {
        expect:
        stringCalculator.add('1,2') == 3
    }

    def 'returns the sum of n numbers'() {
        expect:
        stringCalculator.add("1${DEL}2${DEL}3${DEL}4${DEL}5${DEL}6") == 21
        stringCalculator.add("1${DEL}2${DEL}3${DEL}4${DEL}5") == 15
    }

    def 'returns the sum of n numbers with new lines in the string'() {
        expect:
        stringCalculator.add("1\n2${DEL}3") == 6
        stringCalculator.add("1\n2${DEL}3\n4") == 10
    }

    def 'returns the sum of n numbers when using a specific delimiter'() {
        expect:
        stringCalculator.add('//[;]\n1;2') == 3
    }

    def 'calling add with a negative number throws and exception'() {
        when:
        stringCalculator.add('1,-1')

        then:
        def ex = thrown(NegativeNumberException)
        ex.message == 'negatives not allowed: [-1]'
    }

    def 'calling add with negatives number throws and exception'() {
        when:
        stringCalculator.add('1,-1,-2,-33')

        then:
        def ex = thrown(NegativeNumberException)
        ex.message == 'negatives not allowed: [-1, -2, -33]'
    }

    @Unroll
    def 'numbers bigger than 1000 should be ignored'() {
        expect:
        stringCalculator.add("1,$number") == result

        where:
        number  | result
        '999'   | 1000
        '1000'  | 1001
        '1001'  | 1
    }

    def 'returns the sum of n numbers when calling add with delimiters of any length'() {
        expect:
        stringCalculator.add('//[***]\n1***2***3') == 6
    }

    def 'returns the sum of n numbers when calling add with multiple delimiters'() {
        expect:
        stringCalculator.add('//[*][%]\n1*2%3') == 6
        stringCalculator.add('//[**][%]\n1**2%3') == 6
    }
}
