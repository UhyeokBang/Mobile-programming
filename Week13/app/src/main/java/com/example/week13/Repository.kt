package com.example.week13

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class Repository(private val table:DatabaseReference){

    suspend fun InsertItem(item: Item) {
        withContext(Dispatchers.IO) {
            table.child(item.itemId.toString()).setValue(item)
        }
    }

    suspend fun UpdateItem(item: Item) {
        withContext(Dispatchers.IO) {
            table.child(item.itemId.toString()).child("itemQuantity").setValue(item.itemQuantity)
        }
    }

    suspend fun DeleteItem(item: Item) {
        withContext(Dispatchers.IO) {
            table.child(item.itemId.toString()).removeValue()
        }
    }

    fun FindItem(itemName: String): Flow<List<Item>> = callbackFlow {
        val query: Query = table.orderByChild("itemName").startAt(itemName).endAt(itemName + "\uf8ff")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<Item>()
                for (itemSnapShot in snapshot.children) {
                    val item = itemSnapShot.getValue(Item::class.java)
                    item?.let { itemList.add(it) }
                }
                trySend(itemList)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        query.addValueEventListener(listener)
        awaitClose {
            query.removeEventListener(listener)
        }
    }

    fun getAllItems(): Flow<List<Item>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<Item>()
                for (itemSnapShot in snapshot.children) {
                    val item = itemSnapShot.getValue(Item::class.java)
                    item?.let { itemList.add(item) }
                }
                trySend(itemList)
            }

            override fun onCancelled(error: DatabaseError) {
                 close(error.toException())
            }
        }
        table.addValueEventListener(listener)
        awaitClose {
            table.removeEventListener(listener)
        }
    }
//    fun getAllItems() = dao.getAllItems()
}