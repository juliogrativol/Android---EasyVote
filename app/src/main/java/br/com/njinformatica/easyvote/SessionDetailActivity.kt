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
import br.com.njinformatica.easyvote.adapter.SessionCandidateAdapter
import br.com.njinformatica.easyvote.viewmodel.SessionCandidateViewModel
import kotlinx.android.synthetic.main.activity_session_detail.*

class SessionDetailActivity : AppCompatActivity() {

    private lateinit var sessionCandidateViewModel: SessionCandidateViewModel
    private lateinit var sessionId : String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_detail)

        sessionId = intent.getStringExtra(SESSION_EXTRA)

        sessionCandidateViewModel = ViewModelProviders.of(this)
                .get(SessionCandidateViewModel::class.java)

        setupRecyclerView()
        subscribe()
    }

    private fun setupRecyclerView(){
        val columns = if(resources.configuration.orientation
                == Configuration.ORIENTATION_PORTRAIT) 1
        else 1

        session_candidate_rc_list.layoutManager = StaggeredGridLayoutManager(columns,
                StaggeredGridLayoutManager.VERTICAL)//LinearLayoutManager(this)
        session_candidate_rc_list.adapter = SessionCandidateAdapter{ session->
            startActivity(Intent(this,
                    SessionDetailActivity::class.java).apply {
                putExtra(SESSION_EXTRA, sessionId)
            })
        }
        sessionCandidateViewModel.getData(sessionId)
    }

    private fun subscribe() {
        sessionCandidateViewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        sessionCandidateViewModel.sessionCandidateList.observe(this, Observer {list->
            val adapter = session_candidate_rc_list.adapter as? SessionCandidateAdapter
            adapter?.setData(list)
        })

        sessionCandidateViewModel.isLoading.observe(this, Observer {
            if (it){
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }
}
