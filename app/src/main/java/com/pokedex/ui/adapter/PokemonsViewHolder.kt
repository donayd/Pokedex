package com.pokedex.ui.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.pokedex.databinding.ItemPokemonBinding
import com.pokedex.domain.model.Pokemon

class PokemonsViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun render(pokemon: Pokemon, onClickListener: (id: Int, sharedView: View) -> Unit) {

        binding.tvName.text = pokemon.name
        val pokemonId = pokemon.id

        ViewCompat.setTransitionName(binding.ivPokemon, "pokemon_$pokemonId")

        itemView.setOnClickListener { onClickListener(pokemon.id, binding.ivPokemon) }

        Glide.with(binding.ivPokemon.context)
            .load(pokemon.image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean = false

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Palette.from(resource!!.toBitmap()).generate { palette ->
                        palette?.let {
                            val rgbColor = it.mutedSwatch?.rgb ?: it.vibrantSwatch?.rgb
                            binding.cvPokemon.setCardBackgroundColor(rgbColor!!)
                        }
                    }
                    return false
                }

            }).into(binding.ivPokemon)
    }

}