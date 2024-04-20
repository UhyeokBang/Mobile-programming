package com.example.formidterm

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A03Theme {
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
class imgViewModel : ViewModel() {
    var imgList = mutableStateListOf<ImgData>()
        private set
    init {
        imgList.add(ImgData(R.drawable.arms, false, "arms"))
        imgList.add(ImgData(R.drawable.ears, false, "ears"))
        imgList.add(ImgData(R.drawable.eyebrows, false, "eyebrows"))
        imgList.add(ImgData(R.drawable.eyes, false, "eyes"))
        imgList.add(ImgData(R.drawable.glasses, false, "glasses"))
        imgList.add(ImgData(R.drawable.hat, false, "hat"))
        imgList.add(ImgData(R.drawable.mouth, false, "mouth"))
        imgList.add(ImgData(R.drawable.mustache, false, "mustache"))
        imgList.add(ImgData(R.drawable.nose, false, "nose"))
        imgList.add(ImgData(R.drawable.shoes, false, "shoes"))
    }
    fun changeChecked(index: Int) {
        imgList[index] = imgList[index].copy(checked = !imgList[index].checked)
    }
}
@Composable
fun checkboxWithText(img: ImgData, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = img.checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = img.imgName,
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview
@Composable
fun MainScreen(imgViewModel: imgViewModel = viewModel()) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeLayout()
    } else {
        PortraitLayout()
    }
}
@Composable
fun LandscapeLayout(imgViewModel: imgViewModel = viewModel()) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.body),
                    contentDescription = "body",
                    modifier = Modifier.size(300.dp)
                )
                imgViewModel.imgList.forEachIndexed { index, imgData ->
                    Image(
                        painter = painterResource(id = imgData.img),
                        contentDescription = imgData.imgName,
                        modifier = Modifier
                            .size(300.dp)
                            // .alpha()를 추가하여 투명도 조절
                            .alpha(if (imgData.checked) 1f else 0f),
                    )
                }
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 30.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (i in 0..9) {
                item() {
                    checkboxWithText(img = imgViewModel.imgList[i]) {
                        imgViewModel.changeChecked(i)
                    }
                }
            }
        }
    }
}
@Composable
fun PortraitLayout(imgViewModel: imgViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.body),
                contentDescription = "body",
                modifier = Modifier.size(300.dp)
            )
            imgViewModel.imgList.forEachIndexed { index, imgData ->
                Image(
                    painter = painterResource(id = imgData.img),
                    contentDescription = imgData.imgName,
                    modifier = Modifier
                        .size(300.dp)
                        // .alpha()를 추가하여 투명도 조절
                        .alpha(if (imgData.checked) 1f else 0f),
                )
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 30.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            for (i in 0..9) {
                item() {
                    checkboxWithText(img = imgViewModel.imgList[i]) {
                        imgViewModel.changeChecked(i)
                    }
                }
            }
        }
    }
}