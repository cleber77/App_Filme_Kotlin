package com.Cleber.appfilme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Cleber.appfilme.databinding.CategoriaItemBinding
import com.Cleber.appfilme.model.Categoria

class Adapter_Categoria (private val context: Context,private val listaCategorias:MutableList<Categoria>):
    RecyclerView.Adapter<Adapter_Categoria.CategoriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val intemLista = CategoriaItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return  CategoriaViewHolder(intemLista)
    }

    override fun getItemCount() = listaCategorias.size


    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.titulo.text = listaCategorias[position].titulo
    }
    inner class CategoriaViewHolder(binding:CategoriaItemBinding): RecyclerView.ViewHolder(binding.root) {
       val  titulo = binding.textTitulo
    }

}