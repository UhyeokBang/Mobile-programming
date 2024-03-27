package com.example.myapplication

fun main(){
    val intArr = listOf(1,2,3,4,5,6,7,8,9)
    intArr.filter { data -> data > 5 } .forEach{data ->
        println(data)
    }
}
