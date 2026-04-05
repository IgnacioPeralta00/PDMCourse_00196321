package com.fipruno.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fipruno.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
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
                    DiceRollerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DiceRollerApp(
    modifier: Modifier = Modifier,
    diceViewModel: DiceViewModel = viewModel()
) {

    val uiState by diceViewModel.uiState
    DiceButtonAndImage(
        modifier = modifier,
        state = uiState,
        onRoll = { diceViewModel.rollDice() }
    )
}

@Composable
fun DiceButtonAndImage(
    modifier: Modifier = Modifier,
    state: DiceUiState,
    onRoll: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = state.diceImage),
            contentDescription = stringResource(state.imageDescription)
        )
        Button(
            onClick = onRoll,
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.purple_700))
        ) {
            Text(
                text = stringResource(R.string.roll),
                color = colorResource(R.color.white)
            )
        }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceRollerTheme {
        DiceRollerApp()
    }
}