package br.com.njinformatica.easyvote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.njinformatica.easyvote.viewmodel.AddSessionViewModel
import kotlinx.android.synthetic.main.activity_add_session.*
import java.util.concurrent.TimeUnit

class AddSessionActivity : AppCompatActivity() {

    private lateinit var addSessionViewModel: AddSessionViewModel
    private lateinit var login : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_session)

        addSessionViewModel = ViewModelProviders.of(this).get(AddSessionViewModel::class.java)

        login = intent.getStringExtra(LOGIN_EXTRA)

        setupListeners()
        subscribe()
    }

    private fun setupListeners(){
        btn_add_session.setOnClickListener {
            addSessionViewModel.addSession(login, add_session_name.text.toString(), add_session_qtd_vagas.text.toString());
            TimeUnit.SECONDS.sleep(1L)
            finish()
        }

        btn_cancel_add_session.setOnClickListener {
            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    private fun subscribe(){
        addSessionViewModel.message.observe(this,  Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        addSessionViewModel.isLoading.observe(this,  Observer {
            if (it) {
                progressBar2.visibility = ProgressBar.VISIBLE
            } else {
                progressBar2.visibility = ProgressBar.GONE
            }
        })
    }
}
