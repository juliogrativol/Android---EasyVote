package br.com.njinformatica.easyvote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.njinformatica.easyvote.model.Session

class SessionViewModel : ViewModel() {

    val message: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    val sessionList = MutableLiveData<List<Session>>()

    fun getData(){
        sessionList.value = listOf<Session>(Session("teste_login", "true", "Votação para presbítero", "xxcc", 1 ), Session("teste_login", "true", "Votação para presbítero do rj dia 13", "xxcc", 1 ))
    }
}