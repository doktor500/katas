class FizzBuzz {

    private static final VALUES = [Fizz: 3, Buzz: 5]

    String say(Integer number) {
        VALUES.inject('') { result, entry ->
            result += (number % entry.value == 0 || number.toString().contains(entry.value.toString())) ? entry.key : ''
        }
    }
}

