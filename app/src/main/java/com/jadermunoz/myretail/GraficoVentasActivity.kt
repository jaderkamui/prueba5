package com.jadermunoz.myretail

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.jadermunoz.myretail.ui.theme.Prueba5Theme // Importa tu tema

class GraficoVentasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prueba5Theme { // Usa tu tema aquí
                GraficoVentasScreen()
            }
        }
    }
}

@Composable
fun GraficoVentasScreen() {
    Column { // Envuelve el gráfico en un Column para que se muestre correctamente
        val context = LocalContext.current

        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, 10f))
        entries.add(Entry(1f, 12f))
        entries.add(Entry(2f, 8f))
        entries.add(Entry(3f, 15f))
        entries.add(Entry(4f, 9f))

        val dataSet = LineDataSet(entries, "Ventas")
            dataSet.color = Color.BLUE // Usa android.graphics.Color

            val data = LineData(dataSet)

            AndroidView(
                factory = { context ->
                    LineChart(context).apply {
                        this.data = data

                        description.isEnabled = false // Oculta la descripción

                        invalidate() // Refresca el gráfico
                    }
                }
            )
        }
}