package br.com.njinformatica.easyvote

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        session_vote_list.adapter = SessionVoteAdapter{ session->
            startActivity(Intent(this,
                    VoteSessionActivity::class.java).apply {
                putExtra(SESSION_EXTRA, sessionId)
            })
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
