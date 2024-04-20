package com.example.formidterm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CheckBoxItem(text: String, onChange: () -> Unit) {
    // 체크박스의 체크 상태를 저장하는 상태 변수
    var isChecked = remember { mutableStateOf(false) }

    // 체크박스를 표시
    Checkbox(
        checked = isChecked.value,
        onCheckedChange = { isChecked.value = it },
        modifier = Modifier.padding(8.dp)
    )
    Text(
        text = text,
        modifier = Modifier.padding(start = 4.dp)
    )
}

class MyViewModel: ViewModel(){
    var isArmsVisible = mutableStateOf(true);
    var isEarsVisible = mutableStateOf(true);
    var isEyebrowsVisible = mutableStateOf(true);
    var isEyesVisible = mutableStateOf(true);
    var isGlassesVisible = mutableStateOf(true);
    var isHatVisible = mutableStateOf(true);
    var isMouthVisible = mutableStateOf(true);
    var isMustacheVisible = mutableStateOf(true);
    var isNoseVisible = mutableStateOf(true);
    var isShoesVisible = mutableStateOf(true);

    fun imghide (part: String, flag:Boolean){
        when(part){
            "arms" -> isArmsVisible.value=flag
            "ears" -> isEarsVisible.value=flag
            "eyebrows" -> isEarsVisible.value=flag
            "eeys" -> isEarsVisible.value=flag
            "glasses" -> isEarsVisible.value=flag
            "hat" -> isEarsVisible.value=flag
            "mouth" -> isEarsVisible.value=flag
            "mustache" -> isEarsVisible.value=flag
            "nose" -> isEarsVisible.value=flag
            "shoes" -> isEarsVisible.value=flag
        }
    }
}

@Composable
fun MainScreen() {
    val viewModel: MyViewModel = viewModel()

    val arms: Painter = painterResource(id = R.drawable.arms)
    val body: Painter = painterResource(id = R.drawable.body)
    val ears: Painter = painterResource(id = R.drawable.ears)
    val eyebrows: Painter = painterResource(id = R.drawable.eyebrows)
    val eyes: Painter = painterResource(id = R.drawable.eyes)
    val glasses: Painter = painterResource(id = R.drawable.glasses)
    val hat: Painter = painterResource(id = R.drawable.hat)
    val mouth: Painter = painterResource(id = R.drawable.mouth)
    val mustache: Painter = painterResource(id = R.drawable.mustache)
    val nose: Painter = painterResource(id = R.drawable.nose)
    val shoes: Painter = painterResource(id = R.drawable.shoes)

    var isArmsVisible by rememberSaveable{ mutableStateOf(true) }
//    var isEarsVisible by rememberSaveable { mutableStateOf(true) }
//    var isEyebrowsVisible by rememberSaveable { mutableStateOf(true) }
//    var isEyesVisible by rememberSaveable { mutableStateOf(true) }
//    var isGlassesVisible by rememberSaveable { mutableStateOf(true) }
//    var isHatVisible by rememberSaveable { mutableStateOf(true) }
//    var isMouthVisible by rememberSaveable { mutableStateOf(true) }
//    var isMustacheVisible by rememberSaveable { mutableStateOf(true) }
//    var isNoseVisible by rememberSaveable { mutableStateOf(true) }
//    var isShoesVisible by rememberSaveable { mutableStateOf(true) }

//    var isEarsVisible by remember { mutableStateOf(true) }
//    var isEyebrowsVisible by remember { mutableStateOf(true) }
//    var isEyesVisible by remember { mutableStateOf(true) }
//    var isGlassesVisible by remember { mutableStateOf(true) }
//    var isHatVisible by remember { mutableStateOf(true) }
//    var isMouthVisible by remember { mutableStateOf(true) }
//    var isMustacheVisible by remember { mutableStateOf(true) }
//    var isNoseVisible by remember { mutableStateOf(true) }
//    var isShoesVisible by remember { mutableStateOf(true) }

    Text(
        text = "202011300방우혁",
        modifier = Modifier.padding(vertical = 8.dp)
            .size(300.dp)
    )

    Image(
        painter = body,
        contentDescription = "Image 1",
        modifier = Modifier
            .size(400.dp)
            .padding(top = 10.dp)
    )


    if (isArmsVisible) {
        Image(
            painter = arms,
            contentDescription = "Image 1",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)
        )
    }


    if (viewModel.isEarsVisible.value) {
        Image(
            painter = ears,
            contentDescription = "Image 1",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)
        )
    }


    if (viewModel.isEyebrowsVisible.value) {
        Image(
            painter = eyebrows,
            contentDescription = "Image 2",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)
        )
    }


    if (viewModel.isEyesVisible.value) {
        Image(
            painter = eyes,
            contentDescription = "Image 1",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)
        )
    }


    if (viewModel.isGlassesVisible.value) {
        Image(
            painter = glasses,
            contentDescription = "Image 2",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)
        )
    }


    if (viewModel.isHatVisible.value) {
        Image(
            painter = hat,
            contentDescription = "Image 1",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)
        )
    }


    if (viewModel.isMouthVisible.value) {
        Image(
            painter = mouth,
            contentDescription = "Image 2",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)

        )
    }


    if (viewModel.isMustacheVisible.value) {
        Image(
            painter = mustache,
            contentDescription = "Image 2",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)

        )
    }


    if (viewModel.isNoseVisible.value) {
        Image(
            painter = nose,
            contentDescription = "Image 2",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)

        )
    }


    if (viewModel.isShoesVisible.value) {
        Image(
            painter = shoes,
            contentDescription = "Image 2",
            modifier = Modifier
                .size(400.dp)
                .padding(top = 10.dp)
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isArmsVisible,
                onCheckedChange = { isArmsVisible = it },
                modifier = Modifier.padding(start = 60.dp)
            )

            Text(
                text = "arms",
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = viewModel.isEarsVisible.value,
                onCheckedChange = { viewModel.imghide("ears", it) },
            )
            Text(
                text = "ears",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = viewModel.isEyebrowsVisible.value,
                onCheckedChange = { viewModel.isEyebrowsVisible.value = it },
                modifier = Modifier.padding(start = 60.dp)
            )
            Text(
                text = "eyebrows",
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = viewModel.isEyesVisible.value,
                onCheckedChange = { viewModel.isEyesVisible.value = it },
            )
            Text(
                text = "eyes",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = viewModel.isGlassesVisible.value,
                onCheckedChange = { viewModel.isGlassesVisible.value = it },
                modifier = Modifier.padding(start = 60.dp)
            )
            Text(
                text = "glasses",
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = viewModel.isHatVisible.value,
                onCheckedChange = { viewModel.isHatVisible.value = it },
            )
            Text(
                text = "hat",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = viewModel.isMouthVisible.value,
                onCheckedChange = { viewModel.isMouthVisible.value = it },
                modifier = Modifier.padding(start = 60.dp)
            )
            Text(
                text = "mouth",
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = viewModel.isMustacheVisible.value,
                onCheckedChange = { viewModel.isMustacheVisible.value = it },
            )
            Text(
                text = "mustache",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.padding(bottom = 40.dp, top = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = viewModel.isNoseVisible.value,
                onCheckedChange = { viewModel.isNoseVisible.value = it },
                modifier = Modifier.padding(start = 60.dp)
            )
            Text(
                text = "nose",
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = viewModel.isShoesVisible.value,
                onCheckedChange = { viewModel.isShoesVisible.value = it },
            )
            Text(
                text = "shoes",
                modifier = Modifier.weight(1f)
            )
        }
    }

}


