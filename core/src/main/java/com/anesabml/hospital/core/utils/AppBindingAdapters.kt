package com.anesabml.hospital.core.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.api.load
import com.anesabml.hospital.core.R


@BindingAdapter("isVisible")
fun isVisible(progressBar: ProgressBar, isLoading: Boolean) {
    if (progressBar.isVisible != isLoading) {
        progressBar.visibility =
            if (isLoading) View.VISIBLE
            else View.GONE
    }
}

@BindingAdapter("image")
fun loadImage(imageView: ImageView, photo: String?) {
    if (photo == null || photo.isBlank()) {
        imageView.load(R.drawable.ic_surgeon)
    } else {
        imageView.load(photo) {
            crossfade(true)
            placeholder(R.drawable.ic_surgeon)
        }
    }
}