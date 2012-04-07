import spock.lang.Specification
import spock.lang.Unroll

class StringCalculatorParserSpec extends Specification {

    def stringCalculatorParser = new StringCalculatorParser()
    static final DEL = ','

    @Unroll
    def 'one number string returns a list that contains the number'() {
        expect:
        stringCalculatorParser.convertNumbersStringToList(number) == [number.toInteger()]

        where:
        number << ['1', '2', '33']
    }

    @Unroll
    def 'returns the list of numbers from a string with default delimiters'() {
        expect:
        stringCalculatorParser.convertNumbersStringToList(numbers.join(DEL)) == numbers

        where:
        numbers << [[1, 2], [1, -2], [1000, 2000], [1, 2, 3]]
    }

    def 'returns the list of numbers from a string with new lines in'() {
        expect:
        stringCalculatorParser.convertNumbersStringToList("1\n2${DEL}3") == [1, 2, 3]
        stringCalculatorParser.convertNumbersStringToList("1\n2${DEL}3\n4") == [1, 2, 3, 4]
    }

    def 'returns the list of numbers when using a specific delimiter'() {
        expect:
        stringCalculatorParser.convertNumbersStringToList('//[;]\n1;2') == [1, 2]
    }

    def 'returns the list of numbers when using delimiters of any length'() {
        expect:
        stringCalculatorParser.convertNumbersStringToList('//[***]\n1000***2000***3000') == [1000, 2000, 3000]
    }

    def 'returns the list of numbers when using different delimiters'() {
        expect:
        stringCalculatorParser.convertNumbersStringToList('//[*][%]\n1*2%3') == [1, 2, 3]
        stringCalculatorParser.convertNumbersStringToList('//[**][%]\n1**2%3') == [1, 2, 3]
    }
}
