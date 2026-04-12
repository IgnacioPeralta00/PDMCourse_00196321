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

    var tipInput = ""
    var billInput = ""
    var tip = ""

    fun onTipInputChanged(newInput: String) {
        tipInput = newInput
        _uiState.value = _uiState.value.copy(
            tipPercentage = tipInput
        )
    }

    fun onBillInputChanged(newInput: String) {
        billInput = newInput
        _uiState.value = _uiState.value.copy(
            billAmount = billInput
        )
    }


    fun calculateTip(): String {
        val billAmount = billInput.toDoubleOrNull() ?: 0.0
        val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
        val doubleTip = tipPercent / 100 * billAmount
        tip = NumberFormat.getCurrencyInstance().format(doubleTip)
        return tip
    }

    fun onCalculateTip() {
        val tip = calculateTip()
        _uiState.value = _uiState.value.copy(
            tip = tip,
            billAmount = billInput,
            tipPercentage = tipInput
        )

    }

}