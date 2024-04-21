package com.example.myapplication.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.myapplication.component.TextLazyColumnSwipeToDismiss

@Composable
fun MainScreen3() {
    var dataList= rememberSaveable(saver = listSaver) { mutableStateListOf<String>() }

    if (dataList.isEmpty()) {
        dataList.apply {
            repeat(10) {
                this.add((it + 1).toString())
            }
        }
    }

    Column {
        TextLazyColumnSwipeToDismiss(
            dataList,
            Modifier
                .fillMaxSize()
        )
    }
}