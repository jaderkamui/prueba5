package com.jadermunoz.myretail.activities

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.jadermunoz.myretail.ui.theme.Prueba5Theme

class GraficoVentasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prueba5Theme {
                GraficoVentasScreen()
            }
        }
    }
}

@Composable
fun GraficoVentasScreen() {
    Column {
        val context = LocalContext.current

        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, 10f))
        entries.add(Entry(1f, 12f))
        entries.add(Entry(2f, 8f))
        entries.add(Entry(3f, 15f))
        entries.add(Entry(4f, 9f))

        val dataSet = LineDataSet(entries, "Ventas")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK

        val lineData = LineData(dataSet)

        AndroidView(
            factory = { context ->
                LineChart(context).apply {
                    this.data = lineData
                    description.isEnabled = false
                    invalidate()
                }
            }
        )
    }
}