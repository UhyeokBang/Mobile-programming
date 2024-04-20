package com.example.fortest

//자료형
val b1: Boolean = true

//배열
val numbers = arrayOf(1, 2, 3, 4, 5)

//함수
fun addNumbers(n1: Double, n2: Double): Int {
    val sum = n1 + n2
    val sumInteger = sum.toInt()
    return sumInteger
}

//람다 함수
val add = { x: Int, y: Int -> x + y }

//함수를 인수로 받는 함수
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun main() {
    val sum = calculate(10, 5) { a, b -> a + b }
    println("Sum: $sum") // 출력 결과: Sum: 15

    val difference = calculate(10, 5) { a, b -> a - b }
    println("Difference: $difference") // 출력 결과: Difference: 5
}

//인수
fun highOderFunction1(func: () -> Unit) {
    func()
}

//반환
fun highOderFunction2(): () -> Unit {
    return { println() }
}

//인수 및 반환
fun highOderFunction3(func: () -> Unit): () -> Unit {
    return func
}

//class 선언
class Person (val name:String="noInfo"
              ,
              val addr:String="noInfo"
              ,
              val tel:String="noInfo"){
}

//인스턴스 생성
val person1 = Person("방우혁", "목동", "PhoneNum")


