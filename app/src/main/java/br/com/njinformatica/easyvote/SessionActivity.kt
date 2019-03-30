package br.com.njinformatica.easyvote

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.njinformatica.easyvote.adapter.SessionAdapter
import br.com.njinformatica.easyvote.viewmodel.SessionViewModel
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : AppCompatActivity() {

    private lateinit var sessionViewModel: SessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)

        sessionViewModel = ViewModelProviders.of(this)
                .get(SessionViewModel::class.java)

        setupRecyclerView()
        subscribe()
    }

    private fun setupRecyclerView(){
        val columns = if(resources.configuration.orientation
                == Configuration.ORIENTATION_PORTRAIT) 1
        else 3

        session_list.layoutManager = StaggeredGridLayoutManager(columns,
                StaggeredGridLayoutManager.VERTICAL)//LinearLayoutManager(this)
        session_list.adapter = SessionAdapter()
        sessionViewModel.getData()
    }

    private fun subscribe() {
        sessionViewModel.message.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        sessionViewModel.sessionList.observe(this, Observer {list->
            val adapter = session_list.adapter as? SessionAdapter
            adapter?.setData(list)
        })

        sessionViewModel.isLoading.observe(this, Observer {
            if (it){
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }
}
