package com.beyondthecode.pokekot.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Elías Bellido on 7/15/20
 * Powered by LuminDevs
 * PokeKot Android Project
 */
class SpacesItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.left = 32
        }
    }
}
