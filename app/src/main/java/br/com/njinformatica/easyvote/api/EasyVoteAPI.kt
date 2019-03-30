package br.com.njinformatica.easyvote.api

import br.com.njinformatica.easyvote.model.Login
import br.com.njinformatica.easyvote.model.SessionResponseObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by jthomaz on 29/03/2019.
 */
interface EasyVoteAPI {

    @POST("login")
    fun login(@Body login: Login): Call<Login>

    @GET("session")
    fun listSession(@Query("login") login: String): Call<SessionResponseObject>
}