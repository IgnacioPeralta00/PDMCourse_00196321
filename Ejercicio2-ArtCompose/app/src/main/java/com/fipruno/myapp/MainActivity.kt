package com.fipruno.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fipruno.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)
    val title = stringResource(R.string.title_jetpack_compose_tutorial)
    val mainDescription = stringResource(R.string.compose_short_desc)
    val longDescription = stringResource(R.string.compose_long_desc)

    Column(modifier = Modifier) {
        Image(
            painter = image,
            contentDescription = null,
            modifier.fillMaxWidth()
        )
        Text(modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            text = title)
        Text(modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = mainDescription,
            textAlign = TextAlign.Justify)
        Text(modifier = Modifier.padding(16.dp),
            text = longDescription,
            textAlign = TextAlign.Justify)

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppTheme {
        Greeting(modifier = Modifier)
    }
}