package com.example.week07

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen04(){
    val context = LocalContext.current

    var permissions = arrayOf(
        Manifest.permission.CALL_PHONE,
        Manifest.permission.CAMERA
    )

    val permissionStates =
        rememberMultiplePermissionsState(permissions = permissions.toList())

    var showDialog by remember{
        mutableStateOf(false)
    }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()) {
            if (permissionStates.allPermissionsGranted)
                Toast.makeText(context, "모든 권한이 승인됨", Toast.LENGTH_SHORT).show()
            else {
                if (!permissionStates.shouldShowRationale)
                    showDialog = true
            }
        }

    fun callPhonePermissionGranted() = permissionStates.permissions[0].status.isGranted
    fun cameraPermissionGranted() = permissionStates.permissions[1].status.isGranted

    LaunchedEffect(key1 = permissionStates) {
        if(!permissionStates.allPermissionsGranted && !permissionStates.shouldShowRationale)
            requestPermissionLauncher.launch(permissions)
    }

    if (showDialog) {
        if (permissionStates.shouldShowRationale) {
            ShowCallpermissionRationale(
                onConfirm = {
                    showDialog = false
                    requestPermissionLauncher.launch(permissions)
                },
                onDismiss = {
                    showDialog = false
                },
            )
        } else {
            ShowCallpermissionRationale(
                onConfirm = {
                    showDialog = false
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)
                    )
                    context.startActivity(intent)
                },
                onDismiss = {
                    showDialog = false
                },
            )
        }
    }


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

        Button(onClick = {
            if(callPhonePermissionGranted())
                makeCall(context)
            else
                requestPermissionLauncher.launch(permissions)
        }, modifier = Modifier.width(200.dp)) {
            Text(text = "전화걸기")
        }

        Button(onClick = {
            if(cameraPermissionGranted())
                cameraAction(context)
            else
                requestPermissionLauncher.launch(permissions)
        }, modifier = Modifier.width(200.dp)) {
            Text(text = "카메라")
        }
    }
}

fun cameraAction(context: Context){
    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    context.startActivity(cameraIntent)
}

//fun makeCall(context: Context){
//    val number = Uri.parse("tel:010-1234-1234")
//    val callIntent = Intent(Intent.ACTION_CALL, number)
//    context.startActivity(callIntent)
//}

//@Composable
//fun ShowCallpermissionRationale(
//    onConfirm: () -> Unit,
//    onDismiss: () -> Unit
//){
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        title = { Text(text = "권한 확인요청") },
//        text = { Text(text = "전화걸기 위해서는 CALL_PHONE 권한이 승인되어야 합니다.") },
//        confirmButton = {
//            Button(onClick = onConfirm) {
//                Text(text = "권한승인")
//            }
//        },
//        dismissButton = {
//            Button(onClick = onDismiss) {
//                Text(text = "거부")
//            }
//        }
//    )
//}