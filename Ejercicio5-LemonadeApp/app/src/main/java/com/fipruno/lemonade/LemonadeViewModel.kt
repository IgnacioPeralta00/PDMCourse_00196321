package com.fipruno.lemonade

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

data class LemonadeUiState(
    val imageResource: Int = R.drawable.lemon_tree,
    val stringResource: Int = R.string.lemon_tap,
    val tapsOnButton: Int = 0
)

class LemonadeViewModel : ViewModel() {

    private val _uiState = mutableStateOf(LemonadeUiState())

    val uiState: State<LemonadeUiState> = _uiState

    private var randomSqueezes = (2..4).random()

    fun imageClick() {
        val currentTaps = _uiState.value.tapsOnButton + 1
    }


}