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
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun LemonadeProcess(
    modifier: Modifier,
    lemonadeViewModel: LemonadeViewModel = viewModel()
) {
    val uiState by lemonadeViewModel.uiState
    LemonadeApp(
        state = LemonadeState(
            imageResource = uiState.imageResource,
            stringResource = uiState.stringResource,
        ),
        onImageClick = {
            lemonadeViewModel.imageClick()
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