package com.fipruno.lemonade

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

data class LemonadeUiState(
    val imageResource: Int = R.drawable.lemon_tree,
    val stringResource: Int = R.string.lemon_tap,
    val tapsOnButton: Int = 0
)

class LemonadeViewModel : ViewModel() {

    private val _uiState: MutableState<LemonadeUiState> = mutableStateOf(LemonadeUiState())
    val uiState: State<LemonadeUiState> = _uiState

    private var randomSqueezes = (2..4).random()

    fun imageClick() {
        val currentTaps = _uiState.value.tapsOnButton + 1
        val tapsToDrink = 1 + randomSqueezes
        val tapsToRestart = 3 + randomSqueezes

        if (currentTaps >= tapsToRestart) {
            resetProcess()
            return
        }

        val nextImageResource = when (currentTaps) {
            0 -> R.drawable.lemon_tree
            in 1 until tapsToDrink -> R.drawable.lemon_squeeze
            tapsToDrink -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }

        val nextStringResource = when (currentTaps) {
            0 -> R.string.lemon_tap
            in 1 until tapsToDrink -> R.string.lemon_squeeze
            tapsToDrink -> R.string.lemon_drink
            else -> R.string.lemon_restart
        }

        _uiState.value = _uiState.value.copy(
            imageResource = nextImageResource,
            stringResource = nextStringResource,
            tapsOnButton = currentTaps
        )
    }

    private fun resetProcess() {
        randomSqueezes = (2..4).random()
        _uiState.value = LemonadeUiState() // valores por defecto
    }

}