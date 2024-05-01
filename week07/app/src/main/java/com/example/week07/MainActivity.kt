package com.example.week07

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.week07.ui.theme.Week07Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week07Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val navController = rememberNavController()
//                    LoginNavGraph(navController)
//                    val navController = rememberNavController()
//                    MainScreen(navController)
                    ExampleIntent()
                }
            }
        }
    }
}

@Composable
fun ExampleIntent(){
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = {
            val webPage = Uri.parse("https://m.naver.com")
            val intent = Intent(Intent.ACTION_VIEW, webPage)
            context.startActivity(intent)
        }) {
            Text(text = "네이버")
        }
        Button(onClick = {
            val location = Uri.parse("geo:37.543684,127.077130?z=16")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            context.startActivity(mapIntent)
        }) {
            Text(text = "지도")
        }
        Button(onClick = {
            val message = Uri.parse("sms:010-1234-1234")
            val messageIntent = Intent(Intent.ACTION_SENDTO, message)
            messageIntent.putExtra("sms_body", "밥 먹자....")
            context.startActivity(messageIntent)
        }) {
            Text(text = "문자")
        }
    }
}