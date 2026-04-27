package com.pdm.fipr.androidpedia20

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm.fipr.androidpedia20.routes.Routes
import com.pdm.fipr.androidpedia20.screens.Quiz
import com.pdm.fipr.androidpedia20.screens.Welcome
import com.pdm.fipr.androidpedia20.screens.Result


@Composable
fun QuizNavigation(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(Routes.Welcome)
    val currentIndex = rememberSaveable() { mutableIntStateOf(0) }
    val score = rememberSaveable { mutableIntStateOf(0) }
    val quizAttempt = rememberSaveable {mutableIntStateOf(0) }


    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Welcome> {
                Welcome(
                    modifier = modifier,
                    navigateToQuiz = { backStack.add(Routes.Quiz) }
                )
            }
            entry<Routes.Quiz> {
                key(quizAttempt.intValue) {
                    Quiz(
                        modifier = modifier,
                        questionIndex = currentIndex.intValue,
                        score = score.intValue,
                        onNextQuestion = { currentIndex.intValue++ },
                        onCorrectAnswer = { score.intValue++ },
                        onNavigateToResult = { backStack.add(Routes.Result(score.intValue)) }
                    )
                }
            }
            entry<Routes.Result> { resultRoute ->

                Result(
                    modifier = modifier,
                    score = resultRoute.score,
                    navigateBackToQuiz = {  backStack.removeLastOrNull()
                                            currentIndex.intValue = 0
                                            score.intValue = 0
                                            quizAttempt.intValue++
                                        }
                )
            }
        }

    )

}