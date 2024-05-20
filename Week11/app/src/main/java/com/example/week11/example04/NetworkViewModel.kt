package com.example.week11.example04

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.parser.Parser

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import android.util.Log

class MelonChartViewModel : ViewModel() {

    private val _songList = mutableStateOf<List<SongData>>(emptyList())
    val songList: MutableState<List<SongData>> = _songList

    private val _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: MutableState<Boolean> = _isLoading

    fun fetchMelonChart() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val fetchedSongs = getMelonChart()
                _songList.value = fetchedSongs
            } catch (e: Exception) {
                Log.e("error", "fetch 관련 오류 발생", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    data class SongData(
        var title: String,
        var artist: String,
        var songUrl: String
    )

    private suspend fun getMelonChart(): List<SongData> = withContext(Dispatchers.IO) {
        val url = "https://www.melon.com/chart/index.htm"
        val doc = Jsoup.connect(url)
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
            .get()
        val songElements = doc.select("div.wrap_song_info")

        songElements.map { songElement ->
            val title = songElement.select("div.ellipsis.rank01 > span > a").text()
            val artist = songElement.select("div.ellipsis.rank02 > span > a").text()
            val songUrl = "https://www.melon.com" + songElement.select("div.ellipsis.rank01 > span > a").attr("href")
            SongData(title, artist, songUrl)
        }
    }
}
