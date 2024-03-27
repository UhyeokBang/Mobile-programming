package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImageWithSlot(img:Int, slotBtn:@Composable ()->Unit){
    Image(
        painter = painterResource(id = img),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
    )
    slotBtn()
}

@Composable
fun ButtonWithIcon(counter: Int, onClick:()->Int){
    Button(onClick={onClick()}){
        Icon(Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.Red)
        if(counter>0)
            Text("$counter")
        else
            Text("Like")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconWithBadge(counter: Int, onClick:()->Int){
    Column(modifier = Modifier.padding(16.dp)) {
        BadgedBox(badge = { Badge {
            Text(text = "$counter")
        }
        }){
            Icon(Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.clickable { onClick() })
        }
    }
}

@Composable
fun MainScreen(){
    var counter1 by remember {
        mutableStateOf(10)
    }
    var counter2 by remember {
        mutableStateOf(5)
    }
    var counter3 by remember {
        mutableStateOf(20)
    }

    Column (modifier=Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageWithSlot(img = R.drawable.image1) {
            ButtonWithIcon(counter = counter1, {counter1++})
        }
        ImageWithSlot(img = R.drawable.image2) {
            ButtonWithIcon(counter = counter2, {counter1++})
        }
        ImageWithSlot(img = R.drawable.image3) {
            ButtonWithIcon(counter = counter3, {counter1++})
        }
    }
}