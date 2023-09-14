package com.Cleber.appfilme.View

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Cleber.appfilme.databinding.ActivityFormaLoginBinding

class FormaLogin : AppCompatActivity() {

    private  lateinit var binding: ActivityFormaLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormaLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")
        binding.editEmail.requestFocus()

        binding.editbutaoEntre.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()


                when{
                    email.isEmpty() -> {
                        binding.containerEmail.helperText = "Preencha o email"
                        binding.containerEmail.boxStrokeColor = Color.parseColor("#FF6E40")
                    }
                    senha.isEmpty() -> {
                        binding.containerSenha.helperText = "Preencha  a sua senha"
                        binding.containerSenha.boxStrokeColor = Color.parseColor("#FF6E40")
                    }
                }

           }

        binding.editIncreva.setOnClickListener {

            val int = Intent(this,FormaCadastro::class.java)
            startActivity(int)

          }

        }
     }