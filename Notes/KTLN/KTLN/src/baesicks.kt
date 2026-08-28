package mypackage
fun print(str: String) {
    println(str)
}
/*
fun printnum(num: Int) {
    println("${num+2} + 2ed!")
}
*/
// val and var
// val is read only. It's like const in TypeScript. Basically we can't reassign any value in a val after creation. It's one time only. Just a value.
// var is mutable. Variable for short

// Data types

// INTEGER TYPES
// byte -> 8 bit int (Small int)
// short -> 16 bit in (A bit bigger nums)
// int -> 32 bits
// long -> 64 bits

// FLOATING POINT
// Float -> 32 bits
// double -> 64 bits {Must be explicitly typed}

// BOOLEANS
// true -> 8 bits
// false -> 8 bits

// Character
// Char -> Single character
// String -> A collection of chars

// KOTLIN has type inference basically Kotlin can automatically understand the datatype of our variable
// Explicitly typing the variable types is the best practice for production projects and also migratable to other languages
fun Zariables(name: String="Zebro", age: Int=18, bmi: Float=21.227f, eatsGrass: Boolean = true): String {
    return if (eatsGrass) {
        "$name is a Zebra, whose age is $age years old and bmi is $bmi and it's $eatsGrass that he eats grass!"
    } else {
        "$name is a Zebra, whose age is $age years old and bmi is $bmi and it's $eatsGrass that he doesn't eats grass!"

    }
}
// Operators

// Arithmetic -> +,-,*,/,% For arithmetic
// Logical -> ||. &&. !
// Assignments -> =, +=, -=, /=, *=, %= {'X%=2' means x = x%2}

// Also x++ is prefix basically it returns the value first then increments it and ++x increments first and then return it
// Relational ==, !=, <, <=, >, >=, ===
// == checks functional equality and === checks referential equality basically check both have the same memory address.
fun two_number_analyser_lmao(a :Int = 0, b: Int =0, s1: String = "Zebra", s2: String = "Zebra"): Int {
    println("The two numbers are: $a and $b\n" +
            "Sum: ${a + b}\n" +
            "Difference: ${a - b}\n" +
            "Product: ${a*b}\n" +
            "Division: ${
                try {
                    a / b
                } catch (e: ArithmeticException) {
                    println("Division by zero");
                }
            }\n" +
            "1/Division: ${
                try {
                    b / a
                } catch (e: ArithmeticException) {
                    println("Division by zero");
                }
            }\n" +
            "Remainder: ${
                try {
                    a%b
                } catch (e: ArithmeticException) {
                    println("Division by zero");
                }
            }\n" +
            "Equals: ${if (a==b) "Equal" else "Not Equal"}\n" +
            if (a>=b) "a is at least equal to b" else "b is greater\n" +
                    "${if (s1===s2) {"Same memory"} else {
                        "Different memory"
                    }
                    }"
    //Same memory location: ${if (a===b) "They are in same memory location" else "Not in same memory location"}"


                )

return 0
}
