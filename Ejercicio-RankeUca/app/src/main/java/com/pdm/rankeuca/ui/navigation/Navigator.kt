package com.pdm.rankeuca.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.rankeuca.ui.screens.home.HomeScreen
import com.pdm.rankeuca.ui.screens.option.OptionsScreen
import com.pdm.rankeuca.ui.screens.questionary.QuestionaryScreen
import com.pdm.rankeuca.ui.screens.results.ResultsScreen
import com.pdmcourse2026.RankeUca.ui.screens.question.QuestionScreen

@Composable
fun Navigator() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                HomeScreen(
                    onGestorClick = { backStack.add(Routes.Questions) },
                    onMassiveVoteClick = { backStack.add(Routes.Questionary) }
                )
            }

            entry<Routes.Questionary> {
                QuestionaryScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onVoteSuccess = { mapaDeVotos ->
                        backStack.removeLastOrNull()
                        backStack.add(Routes.Results(userVotes = mapaDeVotos))
                    }
                )
            }

            entry<Routes.Results> { key ->
                ResultsScreen(
                    userVotes = key.userVotes,
                    onBackClick = { backStack.removeLastOrNull() }
                )
            }
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