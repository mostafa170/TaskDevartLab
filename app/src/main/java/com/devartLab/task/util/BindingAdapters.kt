package com.devartLab.task.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {


    @BindingAdapter("imageUrl")
    @JvmStatic //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-static/
    fun loadImage(view: ImageView, url: String) = Picasso.get().load(url).into(view)
}