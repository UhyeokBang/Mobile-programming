package com.example.formidterm
import java.io.File
import java.util.Scanner

fun main(){

    val file = File("words2.txt")
    if(!file.exists()){
        println("202011300 방우혁")
        println("파일 위치를 확인하세요.")
        return
    }

    data class Word (var word: String, var meaning: String) {
    }

    var wordsArray:MutableList<Word> = mutableListOf()
    var string_words:MutableList<String> = mutableListOf()
    var string_meainings:MutableList<String> = mutableListOf()
    var index:Int = 0;
    file.forEachLine {
        if (index % 2 == 0){
            string_words.add(it)
        }else{
            string_meainings.add(it)
        }
        index++
    }
    for(i in 0 until index/2){
        wordsArray.add(Word(string_words[i], string_meainings[i]))
    }
//    for(i in 0 until index/2){
//        println(wordsArray[i])
//    }

    var flag: Int = 1
    val reader = Scanner(System.`in`)
    println("202011300방우혁")
    while(true){
        if(flag==0){
            break;
        }
        println("1) 영어단어 검색 2) 부분 영단어 검색(입력값으로 시작하는) 3) 뜻 검색 4) 종료")
        print("메뉴를 선택하세요 : ")
        var choiced_num = reader.nextInt()
        if(choiced_num == 1){
            reader.nextLine()
            print("찾을 영단어를 정확하게 입력하세요 : ")
            var search_word: String = reader.nextLine()
            if(wordsArray.filter {  it.word == search_word }.isEmpty()){
                println("찾는 단어가 존재하지 않습니다.")
            }
            wordsArray.filter { it.word == search_word }.forEach{ println("${it.word} : ${it.meaning}")}
            println()
        }else if(choiced_num == 2) {
            reader.nextLine()
            print("찾을 영단어의 일부를 입력하세요 : ")
            var search_word: String = reader.next()
            if(wordsArray.filter { it.word.startsWith(search_word) }.isEmpty()){
                println("찾는 단어가 존재하지 않습니다.")
            }
            wordsArray.filter { it.word.startsWith(search_word) }.forEach{ println("${it.word} : ${it.meaning}")}
            println()
        }else if(choiced_num == 3) {
            reader.nextLine()
            print("찾을 뜻을 입력하세요 (뜻의 일부만 포함) : ")
            var search_word: String = reader.next()
            if(wordsArray.filter { it.meaning.contains(search_word) }.isEmpty()){
                println("찾는 단어가 존재하지 않습니다.")
            }
            wordsArray.filter { it.meaning.contains(search_word) }.forEach{ println("${it.word} : ${it.meaning}")}
            println()
        }else if(choiced_num == 4) {
            flag = 0
            println("프로그램을 종료합니다.")
        }else{
            println("잘못된 번호를 입력하였습니다.")
            continue;
        }
    }
}