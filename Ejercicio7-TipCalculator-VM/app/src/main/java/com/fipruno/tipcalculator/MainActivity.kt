package com.fipruno.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fipruno.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TipCalculator(
    modifier: Modifier = Modifier,
    tipCalcViewModel: TipCalcViewModel = viewModel()
) {
    val uiState by tipCalcViewModel.uiState

    TipCalculatorContent(
        modifier = modifier,
        state = uiState,
        onAmountChange = { tipCalcViewModel.onBillInputChanged(it) },
        onTipPercentChange = { tipCalcViewModel.onTipInputChanged(it) },
        onCalculate = { tipCalcViewModel.onCalculateTip() }
    )
}

@Composable
fun TipCalculatorContent(
    modifier: Modifier = Modifier,
    state: TipCalcUiState,
    onAmountChange: (String) -> Unit,
    onTipPercentChange: (String) -> Unit,
    onCalculate: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(17.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),

        ) {
        TextField(
            value = state.billAmount,
            onValueChange = onAmountChange,
            label = { Text("Bill Amount") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            leadingIcon = { Text(text = "$") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = state.tipPercentage,
            onValueChange = onTipPercentChange,
            label = { Text("Tip Percentage") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            leadingIcon = { Text(text = "%") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Button(
            onClick = onCalculate,
            modifier = Modifier
        ) {
            Text(text = "Calculate")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.pale_lavender_light),
                    shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
                .align(Alignment.Start),
        ) {
            Text(
                text = "Tip Amount: ${state.tip}",
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    TipCalculatorTheme {
        TipCalculator( )
    }
}