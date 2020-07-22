package iot.android.cawalkot.presentation.util

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

fun Fragment.navigateTo(direction : NavDirections) {
    findNavController().navigate(direction)
}

fun Fragment.snackBar(message: String) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}
fun View.invisible() {
    this.visibility = View.INVISIBLE
}
fun View.gone() {
    this.visibility = View.GONE
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}
