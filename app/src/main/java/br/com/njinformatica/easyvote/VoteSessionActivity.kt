package br.com.njinformatica.easyvote

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.njinformatica.easyvote.adapter.SessionVoteAdapter
import br.com.njinformatica.easyvote.viewmodel.VoteSessionViewModel
import kotlinx.android.synthetic.main.activity_vote_session.*

class VoteSessionActivity : AppCompatActivity() {

    private lateinit var sessionId : String;
    private lateinit var voteSessionViewModel: VoteSessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote_session)

        sessionId = intent.getStringExtra(SESSION_EXTRA)

        voteSessionViewModel = ViewModelProviders.of(this)
                .get(VoteSessionViewModel::class.java)

        setupRecyclerView()
        subscribe()
    }

    private fun setupRecyclerView(){
        val columns = if(resources.configuration.orientation
                == Configuration.ORIENTATION_PORTRAIT) 1
        else 1

        session_vote_list.layoutManager = StaggeredGridLayoutManager(columns,
                StaggeredGridLayoutManager.VERTICAL)//LinearLayoutManager(this)
        session_vote_list.adapter = SessionVoteAdapter{ candidate ->

            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Confirma o voto para "+candidate.nome +" ?")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Sim", DialogInterface.OnClickListener {
                        dialog, id ->
                        voteSessionViewModel.vote(sessionId, candidate.cpf)
                        Toast.makeText(this, "Voto realizado com sucesso.", Toast.LENGTH_SHORT).show()
                        //finish()
                    })
                    // negative button text and action
                    .setNegativeButton("Não", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Confirmação")
            // show alert dialog
            alert.show()
        }

        voteSessionViewModel.getData(sessionId)
    }

    private fun subscribe() {
        voteSessionViewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        voteSessionViewModel.sessionCandidateList.observe(this, Observer {list->
            val adapter = session_vote_list.adapter as? SessionVoteAdapter
            adapter?.setData(list)
        })

        voteSessionViewModel.isLoading.observe(this, Observer {
            if (it){
                progressBarVoteList.visibility = View.VISIBLE
            } else {
                progressBarVoteList.visibility = View.GONE
            }
        })
    }
}
