package com.example.week12.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun getDao(): ItemDao

    companion object {
        private var database: ItemDatabase? = null
        fun getItemDatabase(context: Context): ItemDatabase{
            return database ?: Room.databaseBuilder(
                context,
                ItemDatabase::class.java,
                "itemdb"
            ).build()
                .also {
                database = it
            }
        }
    }
}

