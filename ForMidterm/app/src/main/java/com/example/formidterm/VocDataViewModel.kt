package com.example.week06.model

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.formidterm.R
import java.util.Scanner

//viewModel은 val vocDataViewModel: VocDataViewModel = viewModel() 이런식으로 선언하면
//걍 알아서 자동으로 알잘딱 VocDataViewModel 로 들어감
example1
class VocDataViewModel(private val application: Application) : AndroidViewModel(application) {

    var vocList = mutableStateListOf<VocData>()
        private set
    init{
        vocList.addAll(readWordFile())
    }

    private fun readWordFile():MutableList<VocData>{
        val context = application.applicationContext
        val scan = Scanner(context.resources.openRawResource(R.raw.words))
        val wordList = mutableListOf<VocData>()
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            wordList.add(VocData(word, meaning))
        }
        scan.close()
        return wordList
    }

    fun changeOpenStatus(index:Int){
        vocList[index] = vocList[index].copy(isOpen = !vocList[index].isOpen)
    }
}