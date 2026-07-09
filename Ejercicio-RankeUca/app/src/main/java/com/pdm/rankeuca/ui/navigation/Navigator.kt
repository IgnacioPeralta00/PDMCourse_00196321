package com.pdm.rankeuca.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.rankeuca.ui.screens.option.OptionsScreen
import com.pdmcourse2026.RankeUca.ui.screens.question.QuestionScreen

@Composable
fun Navigator() {
    val backStack = rememberNavBackStack(Routes.Questions)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Questions> {
                QuestionScreen(
                    onQuestionClick = { questionId ->
                        backStack.add(Routes.Options(questionId))
                    }
                )
            }
            entry<Routes.Options> { key ->
                OptionsScreen(
                    questionId = key.id,
                    onBackClick = { backStack.removeLastOrNull() }
                )
            }
        },
    )
}