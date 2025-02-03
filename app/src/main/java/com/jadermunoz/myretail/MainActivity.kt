package com.jaderkamui.prueba5

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.jaderkamui.prueba5.ui.theme.Prueba5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaPrincipal()
        }
    }
}

@Composable
fun PantallaPrincipal() {
    val context = LocalContext.current
    Column {
        // Botón para ir a la lista de vendedores
        Button(onClick = {
            val intent = Intent(context, ListaVendedoresActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Lista de Vendedores")
        }

        // Botón para ir a la captura de fotos
        Button(onClick = {
            val intent = Intent(context, CapturaFotoActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Captura de Foto")
        }

        // Botón para ir al gráfico de ventas
        Button(onClick = {
            val intent = Intent(context, GraficoVentasActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Gráfico de Ventas")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Prueba5Theme {
        PantallaPrincipal()
    }
}