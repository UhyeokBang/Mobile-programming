package com.example.week12.viemodel

import com.example.week12.roomDB.Item
import com.example.week12.roomDB.ItemDatabase

class Repository(private val db:ItemDatabase){
    val dao = db.getDao()

    suspend fun InsertItem(item: Item){
        dao.InsertItem(item)
    }

    suspend fun UpdateItem(item: Item){
        dao.UpdateItem(item)
    }

    suspend fun DeleteItem(item: Item){
        dao.DeleteItem(item)
    }

    fun getAllItems() = dao.getAllItems()
}