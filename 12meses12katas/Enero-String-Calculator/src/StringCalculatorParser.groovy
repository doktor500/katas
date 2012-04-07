class StringCalculatorParser {

    private static final NEW_LINE_CHARACTER = '\n'
    private static final DEFAULT_DELIMITER = ','
    private static final DELIMITER_PREFIX = '//'
    private static final DELIMITER_INIT_CHAR = '['
    private static final DELIMITER_END_CHAR = ']'
    private static final EMPTY_CHAR = ''
    private static final DELIMITER_INDEX = 2

    List<Integer> convertNumbersStringToList(String numbers) {
        def parsedNumbers = hasDelimiters(numbers) ? convertToDefaultFormat(numbers) : replaceNewLineCharacter(numbers)
        parsedNumbers.split(DEFAULT_DELIMITER)*.toInteger()
    }

    private hasDelimiters(numbers) {
        numbers.contains(DELIMITER_PREFIX)
    }

    private convertToDefaultFormat(numbers) {
        def delimiterList = extractDelimiters(numbers.substring(DELIMITER_INDEX, numbers.indexOf(NEW_LINE_CHARACTER)))
        replaceNonDefaultDelimiters(getNumbersSubstring(numbers), delimiterList)
    }

    private replaceNewLineCharacter(numbers) {
        numbers.replace(NEW_LINE_CHARACTER, DEFAULT_DELIMITER)
    }

    private extractDelimiters(numbers) {
        def delimiters = numbers.replace(DELIMITER_INIT_CHAR, EMPTY_CHAR).replace(DELIMITER_END_CHAR, DEFAULT_DELIMITER)
        delimiters.split(DEFAULT_DELIMITER)
    }

    private getNumbersSubstring(numbers) {
        numbers.substring(numbers.indexOf(NEW_LINE_CHARACTER + 1), numbers.length())
    }

    private replaceNonDefaultDelimiters(numbers, delimiterList) {
        delimiterList.inject(numbers) { parsedNumbers, delimiter -> parsedNumbers.replace(delimiter,DEFAULT_DELIMITER) }
    }
}