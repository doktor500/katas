import spock.lang.Specification
import spock.lang.Unroll

class RomanNumeralsSpec extends Specification {
    
    def romanNumerals = new RomanNumerals()

    @Unroll
    def 'convert between roman an arabic numbers'()
    {
        expect:
        romanNumerals.convertToRomanNumber(number) == roman
        romanNumerals.convertToNumber(roman) == number

        where:
        number | roman
        1      | 'I'
        2      | 'II'
        3      | 'III'
        4      | 'IV'
        5      | 'V'
        6      | 'VI'
        7      | 'VII'
        8      | 'VIII'
        9      | 'IX'
        10     | 'X'
        1984   | 'MCMLXXXIV'
        1991   | 'MCMXCI'
        2008   | 'MMVIII'
        2012   | 'MMXII'
    }
}
