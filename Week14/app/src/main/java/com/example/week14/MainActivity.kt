package com.example.week14

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.week14.ui.theme.Week14Theme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
//    override fun onStart() {
//        super.onStart()
//        Log.i("text", "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.i("text", "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.i("text", "onPause")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.i("text", "onStop")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.i("text", "onDestroy")
//    }
//
//    override fun onNewIntent(intent: Intent) {
//        super.onNewIntent(intent)
//        handleIntent(intent)
//    }

    fun handleIntent(intent: Intent) {
        val msgSender = intent.getStringExtra("msgSender")
        val msgBody = intent.getStringExtra("msgBody")

        setContent {
            Week14Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(msgSender, msgBody)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let { handleIntent(it) }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(msgSender: String? = null, msgBody: String? = null) {
    var msg by remember {
        mutableStateOf("")
    }

    val permission = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.POST_NOTIFICATIONS,
            android.Manifest.permission.RECEIVE_SMS
        )
    )

    val smsPermission =
        rememberPermissionState(permission = android.Manifest.permission.RECEIVE_SMS)
    if (smsPermission.status.isGranted) {
        LaunchedEffect(key1 = msgBody) {
            msgBody?.let {
                msg = it
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        permission.launchMultiplePermissionRequest()
    }

    Column {
        Text(text = "수신 메세지")
        Text(text = msg)
    }
}

class Myapplication:Application(){

    var isForeground = false
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object:ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
                isForeground = true
            }

            override fun onActivityPaused(activity: Activity) {
                isForeground = false
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }
}