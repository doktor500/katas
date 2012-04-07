class StringCalculator {

    private static final MAX_OPERATOR = 1000
    def stringCalculatorParser

    Integer add(String numbers) throws NegativeNumberException {
        def sum = 0
        if (numbers) {
            def numbersList = stringCalculatorParser.convertNumbersStringToList(numbers)
            sum = sumListOfNumbers(numbersList)
            checkForNegativeNumbers(numbersList)
        }
        sum
    }

    private sumListOfNumbers(numbersList) {
        numbersList.collect { (it in (0..MAX_OPERATOR)) ? it : 0 }.sum()
    }

    private checkForNegativeNumbers(numbersList) {
        def negativeNumbers = numbersList.findAll { it < 0 }
        if (negativeNumbers) {
            throw new NegativeNumberException("negatives not allowed: ${negativeNumbers}")
        }
    }
}
