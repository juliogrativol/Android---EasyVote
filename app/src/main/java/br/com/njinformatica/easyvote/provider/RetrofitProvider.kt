package br.com.njinformatica.easyvote.provider

import br.com.njinformatica.easyvote.api.EasyVoteAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jthomaz on 29/03/2019.
 */
object  RetrofitProvider {
    private const val BASE_URL = "https://a2bvgv64ll.execute-api.us-east-1.amazonaws.com/dev/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val esasyVoteAPI = retrofit.create(EasyVoteAPI::class.java)
}