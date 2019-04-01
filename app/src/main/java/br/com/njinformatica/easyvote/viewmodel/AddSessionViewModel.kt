package br.com.njinformatica.easyvote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.njinformatica.easyvote.api.EasyVoteAPI
import br.com.njinformatica.easyvote.model.Session
import br.com.njinformatica.easyvote.provider.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSessionViewModel : ViewModel() {

    val message: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    val easyVoteApi: EasyVoteAPI = RetrofitProvider.esasyVoteAPI

    fun addSession(login: String, sessionName : String, quantidadeVagas : String) {

        isLoading.value = true

        if (validaForm(login, sessionName, quantidadeVagas)) {

            addSession(Session(login, "true", sessionName, "", quantidadeVagas.toLong()),
                    {
                        message.value = "Sessão criada com sucesso."
                        isLoading.value = false
                    },
                    {
                        message.value = "Falha ao criar Sessão."
                        isLoading.value = false
                    })
        } else {
            message.value = "Dados Obrigatórios obrigatórios!"
            isLoading.value = false
        }
    }

    private fun validaForm(login: String, sessionName : String, quantidadeVagas : String): Boolean {
        return !(login.isNullOrBlank() || sessionName.isNullOrBlank() || (quantidadeVagas.isNullOrBlank()) )
    }

    private fun addSession(session: Session, succesAction: ()->Unit, failureAction: ()->Unit){
        val call = easyVoteApi.addSession(session)
        call.enqueue(object : Callback<Session> {
            override fun onFailure(call: Call<Session>, t: Throwable) {
                failureAction()
            }
            override fun onResponse(call: Call<Session>, response: Response<Session>) {
                if (response.isSuccessful){
                    succesAction()
                }else{
                    failureAction()
                }
            }
        })
    }
}