package com.dreammkr.pokedex.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.dreammkr.pokedex.R
import com.dreammkr.pokedex.domain.model.Pokemon


class PokemonsAdapter(
    private var pokemonList: List<Pokemon>,
    private var onClickListener: (id: Int, sharedView: View) -> Unit
) :
    RecyclerView.Adapter<PokemonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonsViewHolder(
            layoutInflater.inflate(
                R.layout.item_pokemon, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonsViewHolder, id: Int) {
        holder.render(pokemonList[id], onClickListener)
        setAnimation(holder.itemView)
    }

    override fun getItemCount(): Int = pokemonList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updatePokemons(pokemons: List<Pokemon>) {
        this.pokemonList = pokemons
        notifyDataSetChanged()
    }

    private fun setAnimation(view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.zoom_in))
    }

}