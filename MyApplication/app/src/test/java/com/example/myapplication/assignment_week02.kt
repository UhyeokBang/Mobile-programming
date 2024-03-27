package com.example.myapplication
import java.util.Scanner

fun main(){
    val nameArray = arrayOf("김길동", "오길동", "이길동", "차길동", "차승원", "차은우", "홍길동")
    var flag: Int = 1
    val reader = Scanner(System.`in`)
    while(true){
        if(flag==0){
            break;
        }
        println("202011300방우혁")
        print("1) 오름차순 2) 내림차순 3) 차씨 찾기 4) 종료 : ")
        var choiced_num = reader.nextInt()
        if(choiced_num == 1){
            nameArray.sorted().forEach { println(it) }
            println()
        }else if(choiced_num == 2) {
            nameArray.sorted().reversed().forEach { println(it) }
            println()
        }else if(choiced_num == 3) {
//            nameArray.filter{it[0].equals("차")}.forEach{ println(it) }
            nameArray.filter{ it.startsWith("차") }.forEach { println(it) }
            println()
        }else if(choiced_num == 4) {
            flag = 0
        }else{
            println("잘못된 번호를 입력하였습니다.")
            continue;
        }
    }
}