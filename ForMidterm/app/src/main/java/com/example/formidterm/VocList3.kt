package com.example.week06.component

import android.speech.tts.TextToSpeech
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week06.model.VocData
import com.example.week06.model.VocDataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocList3(vocDataViewModel: VocDataViewModel = viewModel()) {

    val context = LocalContext.current
    var ttsReady: Boolean by rememberSaveable {
        mutableStateOf(false)
    }
    var tts: TextToSpeech? by rememberSaveable {
        mutableStateOf(null)
    }

    val listState = rememberLazyListState()

    val showScrollToTopButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    DisposableEffect(LocalLifecycleOwner.current) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                ttsReady = true
                tts!!.language = Locale.US
            }
        }
        onDispose {
            tts?.stop()
            tts?.shutdown()
        }
    }

    val speakWord = { vocData: VocData ->
        if (ttsReady) {
            tts?.speak(vocData.word, TextToSpeech.QUEUE_ADD, null, null)
        }
    }

    val coroutineScope = rememberCoroutineScope()
    
    Text(text = "202011300방우혁")

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(vocDataViewModel.vocList,
                key = { _, voc -> voc.word }) { index: Int, item: VocData ->
                val state = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
                            vocDataViewModel.vocList.remove(item)
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismiss(
                    state = state,
                    background = {
                        val color = when (state.dismissDirection) {
                            DismissDirection.EndToStart -> Color.LightGray
                            else -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize()
                                .background(color)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    },
                    dismissContent = {
                        VocItem(vocData = item) {
                            speakWord(item)
                            vocDataViewModel.changeOpenStatus(index)
                        }
                    }
                )
            }
        }


        AnimatedVisibility(
            visible = showScrollToTopButton,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "ScrollToTop",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

