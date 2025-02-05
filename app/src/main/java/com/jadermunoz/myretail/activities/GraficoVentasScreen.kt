package com.jadermunoz.myretail.activities

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import androidx.navigation.NavController

@Composable
fun GraficoVentasScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
        // Datos de ventas por vendedor
        val salesData = mapOf(
            "Rick Sanchez" to 10f,
            "Morty Smith" to 12f,
            "Summer Smith" to 8f,
            "Jerry Smith" to 15f,
            "Beth Smith" to 9f
        )

        val entries = ArrayList<Entry>()
        var index = 0f
        salesData.forEach { (salesperson, sales) ->
            entries.add(Entry(index, sales))
            index++
        }

        val dataSet = LineDataSet(entries, "Ventas")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        dataSet.setDrawFilled(true)
        dataSet.fillColor = Color.LTGRAY
        dataSet.fillAlpha = 100
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 5f
        dataSet.setCircleColor(Color.BLUE)
        dataSet.valueTextSize = 12f // Aumentar el tamaño del texto de los valores

        val lineData = LineData(dataSet)

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { chartContext ->
                LineChart(chartContext).apply {
                    // Asignar lineData al LineChart
                    data = lineData
                    description.isEnabled = false
                    invalidate()

                    // Configuración del eje X
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.granularity = 1f
                    xAxis.isGranularityEnabled = true
                    xAxis.valueFormatter = IndexAxisValueFormatter(salesData.keys.toTypedArray())
                    xAxis.labelRotationAngle = -45f
                    xAxis.textSize = 14f // Aumentar el tamaño del texto del eje X
                    xAxis.textColor = Color.BLACK

                    // Configuración del eje Y
                    axisLeft.axisMinimum = 0f
                    axisRight.isEnabled = false
                    axisLeft.textColor = Color.BLACK
                    axisLeft.textSize = 12f

                    // Animación
                    animateX(1000)
                }
            }
        )
    }
}