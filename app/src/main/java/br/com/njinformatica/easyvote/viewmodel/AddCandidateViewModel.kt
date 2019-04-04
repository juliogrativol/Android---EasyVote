package br.com.njinformatica.easyvote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.njinformatica.easyvote.api.EasyVoteAPI
import br.com.njinformatica.easyvote.model.Candidate
import br.com.njinformatica.easyvote.model.Session
import br.com.njinformatica.easyvote.provider.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCandidateViewModel : ViewModel() {
    val message: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    val easyVoteApi: EasyVoteAPI = RetrofitProvider.esasyVoteAPI

    fun addCandidate(sessao: String, candidateName : String, cpf : String) {

        isLoading.value = true

        if (validaForm(sessao, candidateName, cpf)) {

            addCandidate(Candidate(cpf, candidateName, "true", sessao),
                    {
                        message.value = "Candidato com sucesso."
                        isLoading.value = false
                    },
                    {
                        message.value = "Falha ao criar Candidato."
                        isLoading.value = false
                    })
        } else {
            message.value = "Dados obrigatórios!"
            isLoading.value = false
        }
    }

    private fun validaForm(sessao: String, candidateName : String, cpf : String): Boolean {
        return !(sessao.isNullOrBlank() || candidateName.isNullOrBlank() || (cpf.isNullOrBlank()) )
    }

    private fun addCandidate(candidate: Candidate, succesAction: ()->Unit, failureAction: ()->Unit){
        val call = easyVoteApi.addCandidate(candidate.sessao, candidate)
        call.enqueue(object : Callback<Candidate> {
            override fun onFailure(call: Call<Candidate>, t: Throwable) {
                failureAction()
            }
            override fun onResponse(call: Call<Candidate>, response: Response<Candidate>) {
                if (response.isSuccessful){
                    succesAction()
                }else{
                    failureAction()
                }
            }
        })
    }
}