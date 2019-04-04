package br.com.njinformatica.easyvote.model

import com.google.gson.annotations.SerializedName

class Candidate(var cpf:String, var nome:String, var ativo:String, var sessao:String, var votos : Int = 0) {

}

class SessionCandidateResponseObject(@SerializedName("candidates")
                            var candidates: List<Candidate>)