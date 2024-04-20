package com.example.formidterm

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.util.Scanner

//자료형
val b1: Boolean = true

//배열
val numbers = arrayOf(1, 2, 3, 4, 5)

//읽기 전용 List
val foods: List<String> = listOf("라면", "갈비", "밥")
val foods2 = listOf("라면", "갈비", "밥")

//변경 가능한 List
val r_foods: MutableList<String> = mutableListOf("라면", "갈비", "밥")
val r_foods2 = mutableListOf("라면", "갈비", "밥")

//List 조작
fun ListEdit() {
    r_foods.add("초밥")
    r_foods.removeAt(0)
    r_foods[1] = "부대찌개"
    r_foods.set(1, "김치찌개")
}

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

//입력
fun readExample(){
    //String 입력 : readln 함수
    print("Enter text: ")
    val stringInput = readln()
    println("You entered: $stringInput")

    //자바의 Scanner 객체 사용 (import java.util.Scanner)
    val reader = Scanner(System.`in`)
    print("Enter a number: ")
    var integer:Int = reader.nextInt()
    println("You entered: $integer")
}

// remember
val bill_state = remember {
    mutableStateOf("")
}








