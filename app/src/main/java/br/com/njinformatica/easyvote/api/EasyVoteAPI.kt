package br.com.njinformatica.easyvote.api

import br.com.njinformatica.easyvote.model.*
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

    @POST("session/{sessionId}/candidates")
    fun addCandidate(@Path("sessionId") sessionId: String, @Body candidate: Candidate): Call<Candidate>

    @POST("session/{sessionId}/candidates/{cpf}/votes")
    fun addVote(@Path("sessionId") sessionId: String, @Path("cpf") cpf: String): Call<String>

    @GET("session/{sessionId}/candidates/{cpf}/votes")
    fun getVotes(@Path("sessionId") sessionId: String, @Path("cpf") cpf: String): Call<Votes>
}