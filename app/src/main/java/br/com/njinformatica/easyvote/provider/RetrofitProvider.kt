package br.com.njinformatica.easyvote.provider

import br.com.njinformatica.easyvote.api.EasyVoteAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jthomaz on 29/03/2019.
 */
object  RetrofitProvider {
    private const val BASE_URL = "https://3zg1cigkpk.execute-api.us-east-1.amazonaws.com/v1/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val newsAPI = retrofit.create(EasyVoteAPI::class.java)
}