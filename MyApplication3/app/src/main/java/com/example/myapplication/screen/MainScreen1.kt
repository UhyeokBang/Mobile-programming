package com.example.myapplication.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.myapplication.component.TextLazyColumnBasic
import com.example.myapplication.component.TextLazyRow
import com.example.myapplication.screen.listSaver
import com.example.myapplication.component.TextLazyColumnBasic
import com.example.myapplication.component.TextLazyRow

@Composable
fun MainSceen1() {
    var dataList= rememberSaveable(saver = listSaver) { mutableStateListOf<String>() }

    dataList.apply {
        repeat(10) {
            add((it + 1).toString())
        }
    }


    Column {
        TextLazyRow(
            dataList,
            Modifier
                .fillMaxWidth()
        )

        TextLazyColumnBasic(
            dataList,
            Modifier
                .fillMaxWidth()
        )
    }
}