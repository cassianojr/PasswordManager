package br.com.cassianojunior.passwordmanager.ui.tools

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.StringBuilder
import kotlin.random.Random

class ToolsViewModel : ViewModel() {

    val length = MutableLiveData<Int>().apply {
        value = 4
    }

    val passwordGenerated = MutableLiveData<String>().apply {
        value = ""
    }

    val includeNumbers = MutableLiveData<Boolean>().apply {
        value = false
    }

    val includeLetters = MutableLiveData<Boolean>().apply {
        value = true
    }

    val includeSymbols = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun genRandomPassword(){
        val lowCase = "abcdefghijklmnopqrstuvxyz"
        val upCase = "ABCDEFGHIJKLMNOPQRSTUVXYZ"
        val numbers = "0123456789"
        val specialChars = "£$&()*+[]@#^-_!?"

        val password = StringBuilder()

        if(includeNumbers.value!! || includeLetters.value!! || includeSymbols.value!!){
            val random = Random(System.nanoTime())

            while(password.length != length.value!!){
                val rIndexLetters = random.nextInt(lowCase.length)
                val rIndexNumbers = random.nextInt(numbers.length)
                val rIndexSymbols = random.nextInt(specialChars.length)
                val rTurn = random.nextInt(4)

                if(includeLetters.value!! && rTurn == 0){
                    password.append(lowCase[rIndexLetters])
                }

                if(includeLetters.value!! && rTurn == 1){
                    password.append(upCase[rIndexLetters])
                }

                if(includeNumbers.value!! && rTurn == 2){
                    password.append(numbers[rIndexNumbers])
                }

                if(includeSymbols.value!! && rTurn == 3){
                    password.append(specialChars[rIndexSymbols])
                }
            }
            passwordGenerated.value = password.toString()
        }
    }
}