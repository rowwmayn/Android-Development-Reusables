package com.example.zebraone

sealed class CalculatorOperation(val Symbol:String) {
    object Add: CalculatorOperation("+")
    object Sub: CalculatorOperation("-")
    object Mul: CalculatorOperation("*")
    object Div: CalculatorOperation("/")
}
// A sealed class only allows the Class to have only one of the provided objects.
// Sealed class can be used with when
// Multiple objects can be added later
//