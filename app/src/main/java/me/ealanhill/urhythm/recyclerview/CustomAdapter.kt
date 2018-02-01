package me.ealanhill.urhythm.recyclerview

import android.support.annotation.LayoutRes
import android.view.View

class CustomAdapter<T>(items: List<T>,
                       @LayoutRes layoutRes: Int,
                       private val bindHolder: View.(T) -> Unit)
    : AbstractAdapter<T>(items, layoutRes) {

    private var itemClick: T.() -> Unit = {}

    constructor(items: List<T>,
                @LayoutRes layoutRes: Int,
                bindHolder: View.(T) -> Unit,
                itemClick: T.() -> Unit = {}) : this(items, layoutRes, bindHolder) {
        this.itemClick = itemClick
    }

    override fun onBindViewHolder(holder: Holder, position: Int) =
            holder.itemView.bind(itemList[position])

    override fun onItemClick(itemView: View, position: Int) =
        itemList[position].itemClick()
}