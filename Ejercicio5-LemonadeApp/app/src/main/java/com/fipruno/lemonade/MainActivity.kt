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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
                    LemonadeApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    val randomSqueezes = rememberSaveable() { mutableIntStateOf((2..4).random()) }
    var currentTaps by rememberSaveable() { mutableIntStateOf(0) }

    val tapsToDrink = 1 + randomSqueezes.intValue
    val tapsToRestart = 3 + randomSqueezes.intValue

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
    LemonadeStep(
        modifier = modifier,
        nextImageResource = nextImageResource,
        nextStringResource = nextStringResource,
        onImageClick = {
            currentTaps++
            if (currentTaps >= tapsToRestart) {
                randomSqueezes.intValue = (2..4).random()
                currentTaps = 0
            }
        }
    )
}

@Composable
fun LemonadeStep(
    modifier: Modifier,
    nextImageResource: Int,
    nextStringResource: Int,
    onImageClick: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(25),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.mint_pastel))
            ) {
                Image(
                    painter = painterResource(nextImageResource),
                    contentDescription = stringResource(R.string.lemon_tree_content_description),
                    modifier = Modifier
                        .padding(16.dp)

                )
            }
            Text(
                text = stringResource(nextStringResource),
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
    fun LemonadeAppPreview() {
        LemonadeTheme {
            LemonadeApp()
        }
    }
