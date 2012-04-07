class RomanNumerals {

    private static final DELIMITER = ';'
    private static final NUMBER_TO_ROMAN = [1000: 'M', 900: 'CM', 500: 'D', 400: 'CD', 100: 'C', 90: 'XC',
        50: 'L', 40: 'XL', 10: 'X', 9: 'IX', 5: 'V', 4: 'IV', 1: 'I']

    String convertToRomanNumber(Integer number) {
        def romanNumber = ''
        def lastNumber = number
        while (lastNumber != 0) {
            def entry = NUMBER_TO_ROMAN.find { it.key <= lastNumber }
            lastNumber -= entry.key
            romanNumber += entry.value
        }
        romanNumber
    }

    Integer convertToNumber(String romanNumber) {
        def numbers = romanSymbolToDecimalNumber(romanNumber)
        numbers.split(DELIMITER)*.toInteger().sum()
    }

    private romanSymbolToDecimalNumber(romanNumber) {
        def numbers = romanNumber
        (2..1).each { symbolLength ->
            NUMBER_TO_ROMAN.findAll { it.value.length() == symbolLength }.each {
                numbers = numbers.replace(it.value, it.key.toString() + DELIMITER)
            }
        }
        numbers
    }
}
