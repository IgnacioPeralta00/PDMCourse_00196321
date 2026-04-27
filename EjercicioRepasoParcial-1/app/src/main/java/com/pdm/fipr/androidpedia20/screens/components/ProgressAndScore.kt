package com.pdm.fipr.androidpedia20.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pdm.fipr.androidpedia20.dummy.quizSize

@Composable
fun ProgressAndScore(
    modifier: Modifier = Modifier,
    currentScore: Int,
    currentQuestion: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Score: $currentScore/$quizSize",
        )
        Text(
            text = "Question: $currentQuestion/$quizSize",
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ProgressAndScorePreview(){
    ProgressAndScore(
        modifier = Modifier,
        currentScore = 10,
        currentQuestion = 10
    )

}