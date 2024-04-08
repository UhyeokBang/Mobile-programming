package com.example.lazycomposable.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.lazycomposable.component.TextLazyColumnSwipeToDismiss

@Composable
fun MainSceen3() {
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