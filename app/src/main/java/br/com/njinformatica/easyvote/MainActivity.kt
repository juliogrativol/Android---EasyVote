package br.com.njinformatica.easyvote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.njinformatica.easyvote.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        subscribe()
        setupListeners()
    }

    private fun subscribe(){
        loginViewModel.isLogged.observe(this,  Observer {
            if (it) {
                Toast.makeText(this, "Usuário logado", Toast.LENGTH_SHORT).show()
            }
        })

        loginViewModel.isLogged.observe(this,  Observer {
            if (it) {
                Toast.makeText(this, "Usuário logado", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupListeners(){
        btn_ok.setOnClickListener {
            loginViewModel.login();
        }
    }

}
