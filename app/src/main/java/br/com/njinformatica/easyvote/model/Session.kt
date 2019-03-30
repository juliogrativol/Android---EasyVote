package br.com.njinformatica.easyvote.model

import com.google.gson.annotations.SerializedName

class Session(var login:String, var ativa:String, var titulo:String, var id:String, var quantidade_vagas: Long) {
}

class SessionResponseObject(@SerializedName("sessions")
                         var sessions: List<Session>)