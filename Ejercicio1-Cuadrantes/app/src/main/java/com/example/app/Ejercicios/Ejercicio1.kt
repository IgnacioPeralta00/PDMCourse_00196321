package com.example.app.Ejercicios

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.app.ui.theme.AppTheme

@Composable
fun ColorsApp(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.background(Color.Cyan).weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center ) {
            Text(text = "Ejemplo1")
        }
        Row(modifier = Modifier.weight(1f)) {
            Box(modifier = Modifier.background(Color.Red).weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center) {
                Text(text = "Ejemplo2")
            }
            Box(modifier = Modifier.background(Color.Green).weight(1f).fillMaxHeight(), contentAlignment = Alignment.TopStart) {
                Text(text = "Ejemplo3")
            }
        }
        Box(modifier = Modifier.background(Color.Magenta).weight(1f).fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
            Text(text = "Ejemplo4")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        ColorsApp()
    }
}