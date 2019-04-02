package br.com.njinformatica.easyvote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.njinformatica.easyvote.viewmodel.AddCandidateViewModel
import kotlinx.android.synthetic.main.activity_add_candidate.*
import java.util.concurrent.TimeUnit

class AddCandidateActivity : AppCompatActivity() {

    private lateinit var addCandidateViewModel: AddCandidateViewModel
    private lateinit var session : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_candidate)

        addCandidateViewModel = ViewModelProviders.of(this).get(AddCandidateViewModel::class.java)

        session = intent.getStringExtra(SESSION_EXTRA)

        setupListeners()
        subscribe()
    }

    private fun setupListeners(){
        btn_add_candidate.setOnClickListener {
            addCandidateViewModel.addCandidate(session, text_add_candidate_name.text.toString(), text_add_candidate_cpf.text.toString());
            TimeUnit.SECONDS.sleep(1L)
            finish()
        }

        btn_cancel_add_candidate.setOnClickListener {
            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    private fun subscribe(){
        addCandidateViewModel.message.observe(this,  Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        addCandidateViewModel.isLoading.observe(this,  Observer {
            if (it) {
                progressBar3.visibility = ProgressBar.VISIBLE
            } else {
                progressBar3.visibility = ProgressBar.GONE
            }
        })
    }
}
