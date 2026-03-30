package com.fipruno.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fipruno.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = stringResource(R.string.app_name),
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = colorResource(R.color.yellow_sunflower)
                            )
                        )
                    }
                ) { innerPadding ->
                    LemonadeProcess(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeProcess(modifier: Modifier) {
    var tapsOnButton by remember {mutableStateOf(0)}
    var randomSqueezes by remember { mutableStateOf((2..4).random()) }

    var initialState = LemonadeState()      // valores por defecto 0,0
    var tapsToRestart = 3 + randomSqueezes
    var tapsToDrink = 1 + randomSqueezes

    if (tapsOnButton >= tapsToRestart ) {   // Fin/inicio de un nuevo ciclo
        tapsOnButton = 0
        randomSqueezes = (2..4).random()
    }

    initialState.imageResource = when (tapsOnButton) {
        0 -> R.drawable.lemon_tree
        in 1 until tapsToDrink -> R.drawable.lemon_squeeze
        tapsToDrink -> R.drawable.lemon_drink
        tapsToDrink + 1 -> R.drawable.lemon_restart
        else -> {
            R.drawable.lemon_squeeze
        }
    }
    initialState.stringResource = when (tapsOnButton) {
        0 -> R.string.lemon_tap
        in 1 until tapsToDrink -> R.string.lemon_squeeze
        tapsToDrink -> R.string.lemon_drink
        tapsToDrink + 1 -> R.string.lemon_restart
        else -> {
            R.string.lemon_squeeze
        }
    }

    LemonadeApp(
        state = initialState,
        onImageClick = {
            tapsOnButton++
        },
        modifier = modifier
    )
}


@Composable
fun LemonadeApp(
    modifier: Modifier = Modifier,
    state: LemonadeState,
    onImageClick: () -> Unit = {}
) {

    Column(modifier
        .fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.mint_pastel))
                ) {
                Image(
                    painter = painterResource(state.imageResource),
                    contentDescription = stringResource(R.string.lemon_tree_content_description),
                    modifier = Modifier
                        .padding(16.dp)

                )
            }
            Text(
                text = stringResource(state.stringResource),
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeProcess(modifier = Modifier)
    }
}