package com.example.week13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week13.ui.theme.Week13Theme
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week13Theme {
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
    val table = Firebase.database.getReference("Products/items")
    val viewModel:ItemViewModel = viewModel(factory = ItemViewModelFactory(Repository(table)))

    val itemList by viewModel.itemList.collectAsState(initial = emptyList())
    var selectedItem by remember {
        mutableStateOf<Item?>(null)
    }
    val selectedEvent = {item:Item -> selectedItem = item}

    Column(modifier = Modifier.fillMaxSize()) {
        InputScreen(viewModel = viewModel, selectedItem=selectedItem)
        ItemList(list = itemList, onClick = selectedEvent)

    }
}