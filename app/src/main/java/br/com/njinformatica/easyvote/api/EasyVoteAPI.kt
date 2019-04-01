package br.com.njinformatica.easyvote.api

import br.com.njinformatica.easyvote.model.Login
import br.com.njinformatica.easyvote.model.Session
import br.com.njinformatica.easyvote.model.SessionCandidateResponseObject
import br.com.njinformatica.easyvote.model.SessionResponseObject
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by jthomaz on 29/03/2019.
 */
interface EasyVoteAPI {

    @POST("login")
    fun login(@Body login: Login): Call<Login>

    @GET("session")
    fun listSession(@Query("login") login: String): Call<SessionResponseObject>

    @GET("session/{sessionId}/candidates")
    fun listSessionCandidates(@Path("sessionId") sessionId: String): Call<SessionCandidateResponseObject>

    @POST("session")
    fun addSession(@Body session: Session): Call<Session>
}