package ru.tregubowww.andersen_home_work_6

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ContactItemDecoration(
    private val sidePadding: Int,
    private val topAndBottomPadding: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        outRect.top = topAndBottomPadding
        outRect.bottom = topAndBottomPadding
        outRect.left = sidePadding
        outRect.right = sidePadding
    }
}