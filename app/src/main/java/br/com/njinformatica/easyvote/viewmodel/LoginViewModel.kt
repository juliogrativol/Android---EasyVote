package br.com.njinformatica.easyvote.viewmodel

import androidx.lifecycle.MutableLiveData

/**
 * Created by jthomaz on 29/03/2019.
 */
class LoginViewModel {

    private var _errorMessage: String = ""
        set(value) {
            field = value
            errorMessage.value = value
        }

    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun login() {
        /*
        val call = RetrofitProvider.newsAPI.getAllNews()
        isLoading.value = true
        call.enqueue(object : Callback<NewsResponseObject> {
            override fun onFailure(call: Call<NewsResponseObject>, t: Throwable) {
                _errorMessage = "Deu ruim: ${t.message}"
                isLoading.value = false
            }

            override fun onResponse(call: Call<NewsResponseObject>,
                                    response: Response<NewsResponseObject>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { newsResponseObject ->
                        newsList.value = newsResponseObject.news
                    }
                } else {
                    _errorMessage = "Deu ruim, fale com o admin"
                }
                isLoading.value = false
            }

        })
        */
    }
}