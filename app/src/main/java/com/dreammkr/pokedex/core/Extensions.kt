package com.dreammkr.pokedex.core

import android.app.Activity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide


fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun AppCompatImageView.load(url: Any) {
    url.let {
        Glide.with(this.context).load(url).into(this)
    }
}

fun View.toggleVisibility() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

fun View.animateThis(id: Int) {
    this.startAnimation(AnimationUtils.loadAnimation(this.context, id))
}