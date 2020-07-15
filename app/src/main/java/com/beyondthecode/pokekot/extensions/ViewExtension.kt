package com.beyondthecode.pokekot.extensions

import android.view.View

/**
 * Created by Elías Bellido on 7/15/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
fun View.gone(shouldBeGone: Boolean) {
    visibility = if (shouldBeGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
