package com.example.myapplication.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.component.TextLazyVerticalStaggeredGrid

@Composable
fun MainSceen6() {

    val dataList = rememberSaveable(saver = listSaver) { mutableStateListOf<String>() }

    if (dataList.isEmpty()) {
        dataList.apply {
            repeat(10) {
                add((it + 1).toString())
            }
        }
    }

    Column {
        TextLazyVerticalStaggeredGrid(
            dataList,
            Modifier
                .padding(10.dp)
                .fillMaxSize()
        )
    }
}
