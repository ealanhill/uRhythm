package me.ealanhill.urhythm

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import me.ealanhill.urhythm.util.prepare
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        setData()

        weeklyAverageChart.invalidate()
    }

    private fun setData() {
        weeklyAverageChart.prepare()

        // temporary data
        val values = (1..7).map { it -> Entry(it.toFloat(), Random().nextInt(300 - 50) + 50f) }

        val dataSet = LineDataSet(values, "Test Data")
        dataSet.axisDependency = YAxis.AxisDependency.LEFT
        // smoothes out the line
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.color = ContextCompat.getColor(this, R.color.colorPrimary)
        dataSet.lineWidth = 3f
        dataSet.setDrawValues(false)
        dataSet.setDrawCircles(false)

        val lineData = LineData(dataSet)

        weeklyAverageChart.data = lineData
    }

}