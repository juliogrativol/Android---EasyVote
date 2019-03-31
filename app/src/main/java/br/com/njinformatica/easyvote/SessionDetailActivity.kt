package br.com.njinformatica.easyvote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_session_detail.*

class SessionDetailActivity : AppCompatActivity() {

    private lateinit var sessionId : String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_detail)

        sessionId = intent.getStringExtra(SESSION_EXTRA)

        text_salaId.text = sessionId
    }
}
