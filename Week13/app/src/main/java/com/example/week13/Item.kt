package com.example.week13


data class Item (
    var itemName:String,
    var itemQuantity:Int,
    val itemId:Int=0
){
    constructor():this("noinfo", 0, 0)
}