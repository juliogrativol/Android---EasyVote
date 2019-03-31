package br.com.njinformatica.easyvote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.njinformatica.easyvote.api.EasyVoteAPI
import br.com.njinformatica.easyvote.model.Candidate
import br.com.njinformatica.easyvote.model.SessionCandidateResponseObject
import br.com.njinformatica.easyvote.provider.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionCandidateViewModel : ViewModel(){

    val message: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    val sessionCandidateList = MutableLiveData<List<Candidate>>()

    val easyVoteApi: EasyVoteAPI = RetrofitProvider.esasyVoteAPI

    fun getData(sessionId: String){
        isLoading.value = true

        val call = easyVoteApi.listSessionCandidates(sessionId)
        call.enqueue(object : Callback<SessionCandidateResponseObject> {
            override fun onFailure(call: Call<SessionCandidateResponseObject>, t: Throwable) {
                isLoading.value = false
            }
            override fun onResponse(call: Call<SessionCandidateResponseObject>, response: Response<SessionCandidateResponseObject>) {
                if (response.isSuccessful){
                    response.body()?.let {sessionCandidateResponseObject->
                        sessionCandidateList.value = sessionCandidateResponseObject.candidates
                    }
                }else{
                    message.value = "Falha ao obter lista de Candidatos."
                }

                isLoading.value = false
            }
    })
    }
}