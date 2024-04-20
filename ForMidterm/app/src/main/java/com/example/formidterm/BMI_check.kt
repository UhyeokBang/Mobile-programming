package com.example.formidterm

import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.assignment2.ui.theme.Assignment2Theme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2Theme {
                MainScreen()
            }
        }
    }
}

fun calculateBMI(height: Double,
                 weight: Double,
                 isUnitMeter: Boolean): Any {
    if(height==0.0 && weight == 0.0){
        return "BMI 체크"
    }
    var bmi =  weight/((height)*(height))
    if (!isUnitMeter){
        bmi = weight/((height/100)*(height/100))
    }
    if (bmi<18.5){
        return "저체중"
    }else if(bmi < 25){
        return "정상"
    }else if(bmi < 30){
        return "과체중"
    }else{
        return "비만"
    }
}

@Composable
fun MainScreen() {
    var isUnitMeter by remember {
        mutableStateOf(false)
    }
    var heightInput by remember {
        mutableStateOf("")
    }
    var weightInput by remember {
        mutableStateOf("")
    }
    var heightLabel by remember {
        mutableStateOf("키(cm)")
    }


    val height = heightInput.toDoubleOrNull() ?: 0.0
    val weight = weightInput.toDoubleOrNull() ?: 0.0
    val bmi = calculateBMI(height, weight, isUnitMeter)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "202011300방우혁")
        Row(
            modifier = Modifier
                .padding(bottom = 32.dp,top = 80.dp)
                .align(alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(end=65.dp))
            Text(
                modifier = Modifier.padding(end = 10.dp),
                text = stringResource(id = R.string.enter_height)
            )
            Switch(checked = isUnitMeter, onCheckedChange = {
                isUnitMeter = it
                if(isUnitMeter)
                    heightLabel = "키(m)"
                else
                    heightLabel = "키(cm)"
            })
        }
        TextField(
            value = heightInput,
            onValueChange = { heightInput = it},
            label = { Text(text = heightLabel) },
            modifier = Modifier.padding(start = 65.dp, bottom = 40.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        )
        TextField(
            value = weightInput,
            onValueChange = { weightInput = it},
            label = { Text(text = stringResource(id = R.string.weight_label)) },
            modifier = Modifier.padding(start = 65.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        )
        Text(text = stringResource(id = R.string.BMI_check, bmi),
            modifier = Modifier.padding(start = 65.dp, top = 40.dp),
            style = MaterialTheme.typography.headlineMedium)
    }
}

