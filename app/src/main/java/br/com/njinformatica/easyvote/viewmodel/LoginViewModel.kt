package br.com.njinformatica.easyvote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.njinformatica.easyvote.api.EasyVoteAPI
import br.com.njinformatica.easyvote.model.Login
import br.com.njinformatica.easyvote.provider.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by jthomaz on 29/03/2019.
 */
class LoginViewModel : ViewModel() {

    val message: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()
    var isLogged = MutableLiveData<Boolean>()

    val newsApi: EasyVoteAPI = RetrofitProvider.esasyVoteAPI

    init {
        isLogged.value = false
    }

    fun login(login: String, password: String) {

        isLoading.value = true

        if (validaForm(login, password)) {

            login(Login(login, password),
                    {
                        isLogged.value = true
                        message.value = "Usuário logado com sucesso"
                    },
                    {
                        message.value = "Usuário ou senha inválidos"
                    }
            )
        } else {
            message.value = "Usuário e senha obrigatórios!"
        }

        isLoading.value = false
    }

    private fun validaForm(login: String, password: String): Boolean {
        return !(login.isNullOrBlank() || password.isNullOrBlank())
    }

    private fun login(login: Login, succesAction: ()->Unit, failureAction: ()->Unit){
        val call = newsApi.login(login)
        call.enqueue(object : Callback<Login> {
            override fun onFailure(call: Call<Login>, t: Throwable) {
                message.value = t.message
                isLoading.value = false
                failureAction()
            }

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                if (response.isSuccessful){
                    message.value = "Login efetuado com sucesso"
                    isLoading.value = false
                    succesAction()
                }
            }
        })
    }
}