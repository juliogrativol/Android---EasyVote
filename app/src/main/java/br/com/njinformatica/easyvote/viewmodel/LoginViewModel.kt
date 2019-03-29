package br.com.njinformatica.easyvote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by jthomaz on 29/03/2019.
 */
class LoginViewModel : ViewModel() {

    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    var isLogged = MutableLiveData<Boolean>()

    init{
        isLogged.value = false;
    }

    fun login() {
        isLogged.value = true;
    }
}