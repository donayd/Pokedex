package com.pokedex.ui.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pokedex.databinding.ItemPokemonBinding
import com.pokedex.domain.model.Pokemon
import com.pokedex.ui.view.MainActivity

class PokemonsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun render(pokemon: Pokemon) {
        binding.tvName.text = pokemon.name
        Glide.with(binding.ivPokemon.context).load(pokemon.image).into(binding.ivPokemon)

        itemView.setOnClickListener {
            val intent = Intent(binding.ivPokemon.context, MainActivity::class.java)
            intent.putExtra("ID", pokemon.id)
            binding.ivPokemon.context.startActivity(intent)
        }
    }

}