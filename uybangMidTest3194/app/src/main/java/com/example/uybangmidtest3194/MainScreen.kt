package com.example.uybangmidtest3194

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(80.dp, 80.dp)
            .border(width = 4.dp, color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    var nameInput by remember {
        mutableStateOf("")
    }
    var phoneInput by remember {
        mutableStateOf("")
    }
    val listState = rememberLazyListState()
    val f_name: MutableList<String> = mutableListOf()
    val f_phone: MutableList<String> = mutableListOf()
    val f_counter: MutableList<Int> = mutableListOf()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "202011300방우혁 친구등록", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text(text = "친구이름") },
            modifier = Modifier.padding(start = 65.dp, bottom = 40.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            value = phoneInput,
            onValueChange = { phoneInput = it },
            label = { Text(text = "전화번호") },
            modifier = Modifier.padding(start = 65.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
        )
        Button(onClick = {
            f_name.add(nameInput)
            f_phone.add(phoneInput)
            f_counter.add(0)
        }) {
            Text(text = "추가하기")
        }

        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            stickyHeader {
                Button(onClick={}){
                    Text(
                        text = "친구 목록",
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(5.dp)
                            .fillMaxWidth()
                    )
                }
            }
            itemsIndexed(f_name) { index: Int, item: String ->
                Row() {
                    TextCell(text = item, Modifier.background(Color.Cyan))
                    Button(onClick = { f_counter[index] = f_counter[index] + 1 }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = null,
                            tint =
                            if (f_counter[index] >= 3) {
                                Color.Green
                            } else if (f_counter[index] >= 5) {
                                Color.Red
                            } else {
                                Color.LightGray
                            }
                        )
                    }
                }
            }
        }
    }
}