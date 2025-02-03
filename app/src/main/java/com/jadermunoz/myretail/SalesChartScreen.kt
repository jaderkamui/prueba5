package com.jadermunoz.myretail

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.jadermunoz.myretail.utils.ChartUtils

@Composable
fun SalesChartScreen(navController: NavController) {
    AndroidView(
        factory = { context ->
            createBarChart(context)
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun createBarChart(context: Context): BarChart {
    val barChart = BarChart(context)
    // Obtener datos de ChartUtils
    val (sellerNames, sales) = ChartUtils.prepareSalesData()

    val entries = ArrayList<BarEntry>()
    for (i in sales.indices) {
        entries.add(BarEntry(i.toFloat(), sales[i]))
    }
    val dataSet = BarDataSet(entries, "Ventas")
    dataSet.color = Color.BLUE

    val barData = BarData(dataSet)
    barChart.data = barData

    // Configurar eje X con los nombres de los vendedores
    barChart.xAxis.valueFormatter = IndexAxisValueFormatter(sellerNames)
    barChart.xAxis.granularity = 1f
    barChart.xAxis.setDrawGridLines(false)
    barChart.axisRight.isEnabled = false
    barChart.description.isEnabled = false
    barChart.invalidate()

    return barChart
}
