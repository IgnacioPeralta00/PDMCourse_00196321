package com.pdm.fipr.androidpedia20.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pdm.fipr.androidpedia20.dummy.quizQuestions
import com.pdm.fipr.androidpedia20.model.Question
import com.pdm.fipr.androidpedia20.screens.components.AppScaffold
import com.pdm.fipr.androidpedia20.screens.components.FunFact
import com.pdm.fipr.androidpedia20.screens.components.ProgressAndScore
import com.pdm.fipr.androidpedia20.ui.theme.DarkGreen
import com.pdm.fipr.androidpedia20.ui.theme.LightRed

@Composable
fun Quiz(
    modifier: Modifier = Modifier,
    questionIndex: Int,
    score: Int,
    onNextQuestion: () -> Unit,
    onCorrectAnswer: () -> Unit,
    onNavigateToResult: () -> Unit
) {
    val currentQuestion = quizQuestions[questionIndex]
    var isAnswered by rememberSaveable(questionIndex) { mutableStateOf(false) }
    var selectedOption by rememberSaveable(questionIndex) { mutableStateOf<String?>(null) }

    AppScaffold(
        modifier = modifier,
        title = "Quiz"
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProgressAndScore(
                modifier = Modifier,
                currentScore = score,
                currentQuestion = questionIndex + 1
            )

            QuestionCard(
                modifier = Modifier,
                question = currentQuestion.question
            )

            OptionsButtonList(
                modifier = Modifier,
                currentQuestion = currentQuestion,
                selectedOption = selectedOption ?: "",
                isAnswered = isAnswered,
                onCorrectAnswer = onCorrectAnswer,
                onButtonSelected = { option ->
                    selectedOption = option
                    isAnswered = true

                }
            )

            if (isAnswered) {
                FunFact(funFact = currentQuestion.funFact)

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = if (questionIndex < quizQuestions.size - 1) onNextQuestion else onNavigateToResult,
                    modifier = modifier.align(Alignment.End),
                ) {
                    Text(
                        text = if (questionIndex < quizQuestions.size - 1) "Siguiente" else "Ver resultado"
                    )
                }
            }
            else Spacer(modifier = modifier.weight(1f))
        }
    }


}


@Composable
fun OptionsButtonList(
    modifier: Modifier = Modifier,
    currentQuestion: Question,
    selectedOption: String,
    isAnswered: Boolean,
    onCorrectAnswer: () -> Unit,
    onButtonSelected: (String) -> Unit

    ) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        currentQuestion.options.forEach { option ->
            val isCorrect = (option == currentQuestion.correctAnswer)
            val selectedButton = (option == selectedOption)

            val buttonColor = when {
                !isAnswered -> MaterialTheme.colorScheme.primary
                isCorrect -> DarkGreen
                selectedButton -> LightRed
                else -> MaterialTheme.colorScheme.surfaceVariant
            }

            val textButtonColor = if (isCorrect || selectedButton) Color.White else MaterialTheme.colorScheme.onSurfaceVariant


            Button(
                onClick = {
                    if(!isAnswered) {
                        onButtonSelected(option)
                        if (isCorrect) {
                            onCorrectAnswer()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = !isAnswered,
                shape = Shapes().medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    disabledContainerColor = buttonColor,
                    disabledContentColor = textButtonColor
                )

            )
            {
                Text(text = option)
            }
        }
    }
}

@Composable
fun QuestionCard(
    modifier: Modifier = Modifier,
    question: String
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors()
    ) {
        Text(
            text = question,
            modifier = modifier
                .padding(24.dp),
            textAlign = TextAlign.Center
        )

    }
}