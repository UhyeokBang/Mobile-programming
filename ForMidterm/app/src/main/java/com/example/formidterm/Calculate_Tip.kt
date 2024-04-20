package com.example.formidterm


import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myassignment.ui.theme.MyAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAssignmentTheme {
                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
//                }
                MainScreen()
            }
        }
    }
}

fun calculateTip(amount: Double,
                 tipPercent: Double,
                 roundUp: Boolean): Any {
    var tip = tipPercent / 100 * amount
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditNumberFiled(value:String,
                    onValueChange:(String)->Unit,
                    leadingIcon:ImageVector,
                    label:Int) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = label)) },
        leadingIcon = { Icon(imageVector = leadingIcon, contentDescription = null) },
        modifier = Modifier.padding(32.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
    )

}

@Composable
fun RoundTheTipRow(roundUp:Boolean, onCheckedChange:(Boolean)->Unit){
    Row(
        modifier = Modifier.padding(bottom = 32.dp, start = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier=Modifier.padding( end = 10.dp), text = stringResource(id = R.string.round_up_tip))
        Spacer(modifier=Modifier.padding(start = 40.dp))
        Switch(checked = roundUp, onCheckedChange = onCheckedChange)
    }
}


@Composable
fun MainScreen(){
    var amountInput by remember {
        mutableStateOf("")
    }
    var tipInput by remember {
        mutableStateOf("")
    }
    var roundUp by remember {
        mutableStateOf(false)
    }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent, roundUp)

    Column(modifier = Modifier.padding(40.dp),
        verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )

        EditNumberFiled(amountInput,
            {amountInput = it},
            Icons.Default.Money,
            R.string.bill_amount
        )

//        TextField(
//            value = amountInput,
//            onValueChange = { amountInput = it },
//            label = { Text(text = stringResource(id = R.string.bill_amount)) },
//            leadingIcon = { Icon(imageVector = Icons.Default.Money, contentDescription = null) },
//            modifier = Modifier.padding(32.dp),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
//        )

        EditNumberFiled(tipInput,
            {tipInput = it},
            Icons.Default.Percent,
            R.string.how_was_the_service
        )

//        TextField(
//            value = tipInput,
//            onValueChange = {tipInput = it},
//            label = { Text(text = stringResource(id = R.string.how_was_the_service)) },
//            leadingIcon = { Icon(imageVector = Icons.Default.Percent, contentDescription = null) },
//            modifier = Modifier.padding(32.dp),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
//        )


        Row(
            modifier = Modifier.padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(modifier=Modifier.padding( end = 10.dp), text = stringResource(id = R.string.round_up_tip))
            Spacer(modifier=Modifier.padding(start = 40.dp))
            Switch(checked = roundUp, onCheckedChange = {roundUp = it})
        }

        Text(text = stringResource(id = R.string.tip_amount, tip.toString()),
            style = MaterialTheme.typography.headlineMedium)
    }
}