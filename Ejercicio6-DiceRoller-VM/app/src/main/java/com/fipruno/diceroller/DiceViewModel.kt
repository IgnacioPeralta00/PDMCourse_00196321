package com.fipruno.diceroller

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

data class DiceUiState(
    val diceImage: Int = R.drawable.dice_1,
    val imageDescription: Int = R.string.dice_1,
    val diceValue: Int = 1
)

// Creación del ViewModel
class DiceViewModel : ViewModel() {

    private val _uiState = mutableStateOf(DiceUiState())
    val uiState: State<DiceUiState> = _uiState

    fun rollDice() {

        val rolledNumber = (1..6).random()

        val nextDiceImage = when (rolledNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val nextImageDescription = when (rolledNumber) {
            1 -> R.string.dice_1
            2 -> R.string.dice_2
            3 -> R.string.dice_3
            4 -> R.string.dice_4
            5 -> R.string.dice_5
            else -> R.string.dice_6
        }
        _uiState.value = _uiState.value.copy(
            diceImage = nextDiceImage,
            imageDescription = nextImageDescription,
            diceValue = rolledNumber
            )
        }
    }