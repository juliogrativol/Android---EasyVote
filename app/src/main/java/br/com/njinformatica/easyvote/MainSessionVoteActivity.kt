package br.com.njinformatica.easyvote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sessao_votacao.*

class MainSessionVoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sessao_votacao)

        setupListeners()
    }

    private fun setupListeners(){

        btn_sessao_votacao.setOnClickListener {

            if (validaForm(text_sessao_votacao.text.toString())) {
                startActivity(Intent(this, VoteSessionActivity::class.java).apply {
                    putExtra(SESSION_EXTRA, text_sessao_votacao.text.toString())
                })
            }else{
                Toast.makeText(this, "Sessão obrigatória ou com formato inválido.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validaForm(sessionId: String): Boolean {
        return !(sessionId.isNullOrBlank()) && (sessionId.length <= 5)
    }
}
