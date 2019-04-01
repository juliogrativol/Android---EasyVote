package br.com.njinformatica.easyvote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.njinformatica.easyvote.api.EasyVoteAPI
import br.com.njinformatica.easyvote.model.Login
import br.com.njinformatica.easyvote.model.Session
import br.com.njinformatica.easyvote.model.SessionResponseObject
import br.com.njinformatica.easyvote.provider.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionViewModel : ViewModel() {

    val message: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    val sessionList = MutableLiveData<List<Session>>()

    val easyVoteApi: EasyVoteAPI = RetrofitProvider.esasyVoteAPI

    fun getData(login: String){
        isLoading.value = true

        val call = easyVoteApi.listSession(login)
        call.enqueue(object : Callback<SessionResponseObject> {
            override fun onFailure(call: Call<SessionResponseObject>, t: Throwable) {
                isLoading.value = false
            }
            override fun onResponse(call: Call<SessionResponseObject>, response: Response<SessionResponseObject>) {
                if (response.isSuccessful){
                    response.body()?.let {newsResponseObject->
                        if (newsResponseObject.sessions.size > 0)
                            sessionList.value = newsResponseObject.sessions
                        else
                            message.value = "Não existem sessões cadastradas."
                    }
                }else{
                    message.value = "Falha ao obter lista de Sessões."
                }

                isLoading.value = false
            }
        })
    }
}