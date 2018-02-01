package me.ealanhill.urhythm.util

import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.ealanhill.urhythm.recyclerview.CustomAdapter

infix fun ViewGroup.inflate(@LayoutRes layoutRes: Int) : View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

fun <T> RecyclerView.setUp(items: List<T>,
                           @LayoutRes layoutRes: Int,
                           bindHolder: View.(T) -> Unit,
                           itemClick: T.() -> Unit = {},
                           manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context))
            : CustomAdapter<T> =
    CustomAdapter(items, layoutRes, {
        bindHolder(it)
    }, {
        itemClick()
    }).apply {
        layoutManager = manager
        adapter = this
    }