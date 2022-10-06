package com.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokedex.R
import com.pokedex.domain.model.Pokemon

class PokemonsAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonsViewHolder(
            layoutInflater.inflate(
                R.layout.item_pokemon, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) =
        holder.render(pokemonList[position])

    override fun getItemCount(): Int = pokemonList.size

}