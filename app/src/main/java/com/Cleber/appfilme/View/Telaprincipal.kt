package com.Cleber.appfilme.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.Cleber.appfilme.adapter.Adapter_Categoria
import com.Cleber.appfilme.databinding.ActivityTelaprincipalBinding
import com.Cleber.appfilme.model.Categoria
import com.google.firebase.auth.FirebaseAuth

class Telaprincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaprincipalBinding
    private lateinit var adapterCategoria: Adapter_Categoria
    private val listaCategoria: MutableList <Categoria> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaprincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#090808")

         val recyclerViewFilmes = binding.recycleViewFilmes
             recyclerViewFilmes.layoutManager = LinearLayoutManager(this)
             recyclerViewFilmes.setHasFixedSize(true)
             recyclerViewFilmes.adapter = adapterCategoria
             adapterCategoria = Adapter_Categoria(this,listaCategoria)
             getCategorias()



        binding.texsair.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,FormaLogin::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this,"O usuario sai do App",Toast.LENGTH_SHORT).show()
        }
    }
    private fun getCategorias(){
        val categoria1 = Categoria ("Categorias 1")
        listaCategoria .add(categoria1)

        val categoria2 = Categoria ("Categorias 2")
        listaCategoria .add(categoria2)

        val categoria3 = Categoria ("Categorias 3")
        listaCategoria .add(categoria3)

        val categoria4 = Categoria ("Categorias 4")
        listaCategoria .add(categoria4)
    }

}