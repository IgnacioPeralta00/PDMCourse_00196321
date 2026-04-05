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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fipruno.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
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
                    DiceRollerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DiceRollerApp(modifier: Modifier = Modifier) {

    var diceValue by rememberSaveable { mutableIntStateOf(1) }

    val imageResource = when (diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    val contentDescription = when (diceValue) {
        1 -> R.string.dice_1
        2 -> R.string.dice_2
        3 -> R.string.dice_3
        4 -> R.string.dice_4
        5 -> R.string.dice_5
        else -> R.string.dice_6
    }



    DiceButtonAndImage(
        modifier = modifier,
        imageResource = imageResource,
        contentDescription = contentDescription,
        onButtonClick = { diceValue = (1..6).random() }
    )
}

@Composable
fun DiceButtonAndImage(
    modifier: Modifier = Modifier,
    imageResource: Int,
    contentDescription: Int,
    onButtonClick: () -> Unit
) {

    Column(modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(contentDescription)
        )
        Button(
            onClick = onButtonClick,
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
fun DiceRollerPreview() {
    DiceRollerTheme {
        DiceRollerApp()
    }
}
