package com.example.week12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week12.roomDB.Item
import com.example.week12.roomDB.ItemDatabase
import com.example.week12.screen.InputScreen
import com.example.week12.screen.ItemList
import com.example.week12.ui.theme.Week12Theme
import com.example.week12.viemodel.Repository
import com.example.week12.viewmodel.ItemViewModel
import com.example.week12.viewmodel.ItemViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week12Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val context = LocalContext.current
    val itemdb = ItemDatabase.getItemDatabase(context)
    val viewModel:ItemViewModel = viewModel(factory = ItemViewModelFactory(Repository(itemdb)))

    val itemList by viewModel.itemList.collectAsState(initial = emptyList())
    var selectedItem by remember {
        mutableStateOf<Item?>(null)
    }
    val selectedEvent = {item:Item -> selectedItem = item}

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "202011300방우혁")
        InputScreen(viewModel = viewModel, selectedItem=selectedItem)
        ItemList(list = itemList, onClick = selectedEvent)

    }
}