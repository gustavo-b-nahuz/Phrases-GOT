package com.example.phrasesgot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(): ViewModel() {
    private var nome = MutableLiveData<String>()

    fun changeName(novoNome: String) {
        nome.value = novoNome
    }

    fun getName(): LiveData<String> {
        return nome
    }

}