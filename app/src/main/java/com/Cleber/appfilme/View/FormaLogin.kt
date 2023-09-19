package com.Cleber.appfilme.View

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.Cleber.appfilme.databinding.ActivityFormaLoginBinding
import com.google.firebase.auth.FirebaseAuth

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
                    else -> {
                        autenticacao(email,senha)
                    }
                }

           }

        binding.editIncreva.setOnClickListener {
            val int = Intent(this,FormaCadastro::class.java)
            startActivity(int)
          }

        }
              private fun autenticacao(email: String , senha : String){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener{autenticacao ->
                    if (autenticacao.isSuccessful){
                        Toast.makeText(this," Login efetuado como sucesso! ",Toast.LENGTH_SHORT).show()
                        navegarTelaPrincipal()
                    }
                }.addOnFailureListener{
                    Toast.makeText(this,"Erro ao fazer  o login do usuario!",Toast.LENGTH_SHORT).show()
                }
        }

       private fun  navegarTelaPrincipal(){
           val intent = Intent(this,Telaprincipal::class.java)
           startActivity(intent)
           finish()
         }

    override fun onRestart() {
        super.onRestart()
        val usuarioAtual  = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null){
            navegarTelaPrincipal()
        }

    }

}