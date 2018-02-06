package me.ealanhill.urhythm.util

import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
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

fun LineChart.prepare() {
    this.setBackgroundColor(android.graphics.Color.rgb(255, 255, 255))
    this.setDrawGridBackground(false)
    this.description = null

    // set up x axis
    this.xAxis.setDrawGridLines(false)
    this.xAxis.setDrawAxisLine(false)
    this.xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE

    // set up y axis
    this.axisLeft.isEnabled = false
    this.axisRight.isEnabled = false
    this.legend.isEnabled = false
}