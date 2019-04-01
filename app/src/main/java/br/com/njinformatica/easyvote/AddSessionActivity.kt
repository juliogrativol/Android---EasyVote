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
import kotlinx.android.synthetic.main.activity_main.*

class AddSessionActivity : AppCompatActivity() {

    private lateinit var addSessionViewModel: AddSessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_session)

        addSessionViewModel = ViewModelProviders.of(this).get(AddSessionViewModel::class.java)

        setupListeners()
        subscribe()
    }

    private fun setupListeners(){
        btn_add_session.setOnClickListener {
            addSessionViewModel.addSession("juliogrativol", add_session_name.text.toString(), add_session_qtd_vagas.text.toString());
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
