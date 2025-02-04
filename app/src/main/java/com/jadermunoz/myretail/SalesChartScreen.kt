package com.jadermunoz.myretail

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun SalesChartScreen() {
    val context = LocalContext.current

    val entries = ArrayList<BarEntry>()
    entries.add(BarEntry(0f, 10f))
    entries.add(BarEntry(1f, 12f))
    entries.add(BarEntry(2f, 8f))
    entries.add(BarEntry(3f, 15f))
    entries.add(BarEntry(4f, 9f))

    val dataSet = BarDataSet(entries, "Ventas")
    dataSet.color = Color.BLUE // Usa android.graphics.Color

    val data = BarData(dataSet)

    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                this.data = data

                val xAxis = this.xAxis
                xAxis.valueFormatter = IndexAxisValueFormatter(arrayOf("Semana 1", "Semana 2", "Semana 3", "Semana 4", "Semana 5")) // Etiquetas del eje X

                description.isEnabled = false // Oculta la descripción

                invalidate() // Refresca el gráfico
            }
        }
    )
}