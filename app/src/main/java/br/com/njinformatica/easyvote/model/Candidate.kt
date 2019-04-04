package br.com.njinformatica.easyvote.model

import com.google.gson.annotations.SerializedName

class Candidate(var cpf:String, var nome:String, var ativo:String, var sessao:String) {

}

class SessionCandidateResponseObject(@SerializedName("candidates")
                            var candidates: List<Candidate>)