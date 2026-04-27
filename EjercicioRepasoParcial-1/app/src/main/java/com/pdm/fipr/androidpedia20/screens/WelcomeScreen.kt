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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pdm.fipr.androidpedia20.screens.components.AppScaffold
import com.pdm.fipr.androidpedia20.ui.theme.AndroidPedia20Theme

@Composable
fun Welcome(
    modifier: Modifier,
    navigateToQuiz: (Int) -> Unit
) {

    AppScaffold(
        modifier = modifier,
        title = "AndroidPedia",
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "AndroidPedia By Peralta",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = "¿Cuánto sabes de Android?",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = modifier.height(32.dp))

            Text(
                text = "Fidel Ignacio Peralta Rivas\n 00196321",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = modifier.height(48.dp))

            Button(
                onClick = { navigateToQuiz(0) }) {
                Text(
                    text = "Comenzar Quiz",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    AndroidPedia20Theme {
        Welcome(modifier = Modifier, navigateToQuiz = {})
    }
}
