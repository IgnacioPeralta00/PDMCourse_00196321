package com.fipruno.tipcalc

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fipruno.tipcalc.ui.theme.TipCalcTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalcTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = stringResource(R.string.app_name),
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = colorResource(R.color.purple_700),
                                titleContentColor = colorResource(R.color.white)
                            )
                        )
                    }) { innerPadding ->
                    TipCalculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TipCalculator(modifier: Modifier = Modifier) {
    var amountInput by rememberSaveable { mutableStateOf("") }
    var tipInput by rememberSaveable { mutableStateOf("") }
    var tip by rememberSaveable { mutableStateOf("") }

    TipCalculatorContent(
        modifier = modifier,
        amountInput = amountInput,
        tipInput = tipInput,
        tip = tip,
        onAmountChange = { amountInput = it },
        onTipPercentChange = { tipInput = it },
        onCalculate = {
            val amount = amountInput.toDoubleOrNull() ?: 0.0
            val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
            tip = calculateTip(amount, tipPercent) }
    )
}

@Composable
fun TipCalculatorContent(
    modifier: Modifier = Modifier,
    amountInput: String,
    tipInput: String,
    tip: String,
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
            value = amountInput,
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
            value = tipInput,
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
                text = "Tip Amount: $tip",
                fontStyle = FontStyle.Italic
            )
        }
    }
}

internal fun calculateTip(amount: Double, tipPercent: Double = 15.0) : String {

    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    TipCalcTheme {
        TipCalculator()
    }
}