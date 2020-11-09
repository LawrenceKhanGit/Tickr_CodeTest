package com.practice.mynews.internal

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.practice.mynews.R

@BindingAdapter("imageUrl")
fun ImageView.url(url: String?) {
    val requestOptions = RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_background)

    Glide.with(context)
        .applyDefaultRequestOptions(requestOptions)
        .load( url)
        .into(this)
}