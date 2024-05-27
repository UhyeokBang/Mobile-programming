package com.example.week12.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert
    suspend fun InsertItem(item: Item)

    @Update
    suspend fun UpdateItem(item: Item)

    @Delete
    suspend fun DeleteItem(item: Item)

    @Query("Select * from items")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE itemName LIKE :itemName")
    fun FindItem(itemName:String): Flow<List<Item>>
}