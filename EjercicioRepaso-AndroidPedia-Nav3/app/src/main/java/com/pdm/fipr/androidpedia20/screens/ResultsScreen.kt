package com.pdm.fipr.androidpedia20.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdm.fipr.androidpedia20.dummy.quizSize
import com.pdm.fipr.androidpedia20.screens.components.AppScaffold
import com.pdm.fipr.androidpedia20.ui.theme.AndroidPedia20Theme

@Composable
fun Result(
    modifier: Modifier,
    score: Int,
    navigateBackToQuiz: () -> Unit
) {

    val message = when (score) {
        in 0..1 -> "Te hace falta estudiar un poco más..."
        2 -> "!Muy bien! Puedes hacerlo mejor"
        3 -> "!Felicidades! Eres todo un Androide"
        else -> ""
    }
    AppScaffold(
        modifier = modifier,
        title = "Resultado"

    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Obtuviste $score de $quizSize",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = modifier.height(8.dp))

            Button(
                onClick =  navigateBackToQuiz
            ) {
                Text(text = "Reiniciar Quiz")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    AndroidPedia20Theme {
        //ResultScreen(modifier = Modifier, score = 10, message = "Excelente!")
    }
}