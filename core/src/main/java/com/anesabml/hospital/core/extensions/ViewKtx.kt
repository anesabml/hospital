package com.anesabml.hospital.core.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.showSnackBar(resString: Int, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, resString, length).show()
}