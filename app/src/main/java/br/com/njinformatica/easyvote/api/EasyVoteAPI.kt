package br.com.njinformatica.easyvote.api

import br.com.njinformatica.easyvote.model.Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by jthomaz on 29/03/2019.
 */
interface EasyVoteAPI {

    @POST("login")
    fun login(@Body login: Login): Call<Login>
}