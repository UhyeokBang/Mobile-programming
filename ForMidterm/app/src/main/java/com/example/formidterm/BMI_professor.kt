package com.example.formidterm

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICheckerTheme {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    value: String, onValueChange: (String) -> Unit, label: Int, keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = label)) },
        modifier = Modifier.padding(bottom = 32.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}

@Composable
fun ChangeToMeter(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.padding(
            bottom = 32.dp,
            start = 10.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.to_meter))
        Switch(
            checked = checked, onCheckedChange = onCheckedChange, modifier = Modifier.padding(
                start =
                10.dp
            )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun MainScreen() {
    var heightInput by remember {
        mutableStateOf("")
    }
    var weightInput by remember {
        mutableStateOf("")
    }
    var toMeter by remember {
        mutableStateOf(false)
    }
    val height = heightInput.toDoubleOrNull() ?: 0.0
    val weight = weightInput.toDoubleOrNull() ?: 0.0
    Column(
        modifier = Modifier.padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        ChangeToMeter(checked = toMeter, onCheckedChange = { toMeter = it })
        EditNumberField(
            value = heightInput,
            onValueChange = { heightInput = it },
            label = m_or_cm(toMeter),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next, keyboardType =
                KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        EditNumberField(
            value = weightInput,
            onValueChange = { weightInput = it },
            label = R.string.weight,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType =
                KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        Text(
            text = stringResource(id = checkBMI(calculateBmi(height, weight, toMeter))),
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 100.dp)
        )
    }
}

fun calculateBmi(height: Double, weight: Double, toMeter: Boolean): Double {
    if (toMeter == true)
        return weight / (height * height)
    else
        return weight / (height * height / 10000)
}

fun m_or_cm(toMeter: Boolean): Int {
    if (toMeter == true) {
        return R.string.height_meter
    }
    return R.string.height_senti
}

fun checkBMI(a: Double) =
    when {
        a < 18.5 -> R.string.low
        a < 25 -> R.string.normal
        a < 30 -> R.string.overweight
        a >= 30 -> R.string.obesity
        else -> R.string.bmi_check
    }