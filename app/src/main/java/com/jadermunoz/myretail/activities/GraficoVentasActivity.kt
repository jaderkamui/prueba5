package com.jaderkamui.prueba5

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.jaderkamui.prueba5.ui.theme.Prueba5Theme
import android.graphics.Color.BLACK

class GraficoVentasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraficoVentasScreen()
        }
    }
}

@Composable
fun GraficoVentasScreen() {
    val entries = ArrayList<Entry>()
    entries.add(Entry(0f, 1f))
    entries.add(Entry(1f, 4f))
    entries.add(Entry(2f, 2f))
    entries.add(Entry(3f, 5f))

    val dataSet = LineDataSet(entries, "Label")
    dataSet.color = Color.BLUE
    dataSet.valueTextColor = BLACK

    val lineData = LineData(dataSet)

    // Configuración del gráfico (puedes personalizarlo)
    val chart = LineChart(LocalContext.current)
    chart.data = lineData
    chart.description.isEnabled = false // Desactiva la descripción
    chart.setTouchEnabled(true) // Habilita touch
    chart.setPinchZoom(true) // Habilita zoom con pellizco

    AndroidView(
        factory = { chart },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun GraficoVentasPreview() {
    Prueba5Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GraficoVentasScreen()
        }
    }
}