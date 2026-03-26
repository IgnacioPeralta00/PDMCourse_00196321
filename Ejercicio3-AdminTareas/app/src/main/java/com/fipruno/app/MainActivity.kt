package com.fipruno.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fipruno.app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskAdmn(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskAdmn(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.ic_task_completed), contentDescription = null)
        Text(
            stringResource(R.string.task_completed),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            stringResource(R.string.nice_work),
            fontSize = 16.sp,
            color = Color.Black
        )

    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppTheme {
        TaskAdmn(Modifier)
    }
}