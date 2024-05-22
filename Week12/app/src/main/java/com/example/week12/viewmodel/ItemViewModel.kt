package com.example.week12.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.week12.roomDB.Item
import com.example.week12.viemodel.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ItemViewModel (private val repository: Repository) : ViewModel(){

    private var _itemList = MutableStateFlow<List<Item>>(emptyList())
    val itemList = _itemList.asStateFlow()

    fun InsertItem(item:Item){
        viewModelScope.launch {
            repository.InsertItem(item)
        }
    }

    fun UpdateItem(item:Item){
        viewModelScope.launch {
            repository.UpdateItem(item)
        }
    }

    fun DeleteItem(item:Item){
        viewModelScope.launch {
            repository.DeleteItem(item)
        }
    }

    fun getAllItems(){
        viewModelScope.launch {
            repository.getAllItems().collect{
                _itemList.value = it
            }
        }
    }
   
}