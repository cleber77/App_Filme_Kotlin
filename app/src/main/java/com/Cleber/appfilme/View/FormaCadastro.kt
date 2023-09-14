package com.Cleber.appfilme.View

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.widget.Toast
import com.Cleber.appfilme.R
import com.Cleber.appfilme.databinding.ActivityFormaCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.ktx.Firebase

class FormaCadastro() : AppCompatActivity(), Parcelable {

    private lateinit var binding: ActivityFormaCadastroBinding

    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FormaCadastro> {
        override fun createFromParcel(parcel: Parcel): FormaCadastro {
            return FormaCadastro(parcel)
        }

        override fun newArray(size: Int): Array<FormaCadastro?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")
        binding.editEmail.requestFocus()




        binding.btVamosla.setOnClickListener {
            val email = binding.editEmail.text.toString()

            if (!email.isEmpty()) {
                binding.containerSenha.visibility = View.VISIBLE
                binding.btVamosla.visibility = View.GONE
                binding.btContinuar.visibility = View.VISIBLE
                binding.textTitulo.setText("Um mundo de séries de Filme \n ilimitados espera por você")
                binding.textDescricao.setText("cria uma com ta para saber \n o nosso App  de filme")
                binding.containerEmail.boxStrokeColor = Color.parseColor("#0AFA84")
                binding.containerEmail.helperText = ""
                binding.containerHeader.visibility = View.VISIBLE

            } else {
                binding.containerEmail.boxStrokeColor = Color.parseColor("#CD0F0F")
                binding.containerEmail.helperText = " O email é obrigatorio!"
            }
        }

        binding.btContinuar.setOnClickListener {


            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (!email.isEmpty() && !senha.isEmpty()) {
                cadastro(email, senha)
            } else if (senha.isEmpty()) {
                binding.containerSenha.boxStrokeColor = Color.parseColor("#CD0F0F")
                binding.containerSenha.helperText = " A senha é Obrigatoria"
                binding.containerSenha.boxStrokeColor = Color.parseColor("#0AFA84")
            } else if (email.isEmpty()) {
                binding.containerEmail.boxStrokeColor = Color.parseColor("#CD0F0F")
                binding.containerEmail.helperText = " O email é obrigatorio!"
            }

        }

        binding.txtEntra.setOnClickListener{
          val intent = Intent(this,FormaLogin::class.java)
            startActivity(intent)
        }
    }
    private fun cadastro(email:String ,senha:String){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener{cadastro ->
            if(cadastro.isSuccessful){
                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                binding.containerEmail.helperText  = ""
                binding.containerSenha.helperText = ""
                binding.containerEmail.boxStrokeColor = Color.parseColor("#0AFA84")
                binding.containerSenha.boxStrokeColor = Color.parseColor("#0AFA84")
            }
        }.addOnFailureListener {
           var  erro  = it

            when{
                 erro is  FirebaseAuthWeakPasswordException -> {
                     binding.containerSenha.helperText = "Digite um  senha com minimo 6 caracters!"
                     binding.containerEmail.boxStrokeColor = Color.parseColor("#CD0F0F")
                 }
                erro is FirebaseAuthUserCollisionException -> {
                    binding.containerEmail.helperText = "Esta conta já existe"
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#CD0F0F")
                }
                erro is FirebaseNetworkException ->{
                    binding.containerEmail.helperText = "Voçê esta sem Internet verifica sua conexão"
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#CD0F0F")
                }
                else -> {
                    Toast.makeText(this,"Erro ao cadastra o usuario",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
