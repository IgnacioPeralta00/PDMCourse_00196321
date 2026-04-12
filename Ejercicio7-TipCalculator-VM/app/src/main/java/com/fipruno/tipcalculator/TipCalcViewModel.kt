package com.fipruno.tipcalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import java.text.NumberFormat


data class TipCalcUiState(
    val tip: String = "",
    val billAmount: String = "",
    val tipPercentage: String = ""
)

class TipCalcViewModel(): ViewModel() {

    private val _uiState: MutableState<TipCalcUiState> = mutableStateOf(TipCalcUiState())
    val uiState: State<TipCalcUiState> = _uiState
    fun onTipInputChanged(newInput: String) {
        _uiState.value = _uiState.value.copy(
            tipPercentage = newInput
        )
    }

    fun onBillInputChanged(newInput: String) {
        _uiState.value = _uiState.value.copy(
            billAmount = newInput
        )
    }


    fun onCalculateTip() {
        val bill = _uiState.value.billAmount.toDoubleOrNull() ?: 0.0
        val tipPercent = _uiState.value.tipPercentage.toDoubleOrNull() ?: 0.0
        val calculatedTip = if (bill > 0) (bill * (tipPercent / 100)) else 0.0

        _uiState.value = _uiState.value.copy(
            tip = NumberFormat.getCurrencyInstance().format(calculatedTip)
        )

    }

}