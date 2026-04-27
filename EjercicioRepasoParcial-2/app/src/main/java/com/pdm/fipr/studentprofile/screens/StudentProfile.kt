package com.pdm.fipr.studentprofile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pdm.fipr.studentprofile.screens.components.AppScaffold

@Composable
fun StudentImage(
    modifier: Modifier,
    studentId: Int,
    onBackHome: () -> Unit
) {
    AppScaffold(
        modifier = modifier,
        title = "Imagen"
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/79EVB4qhyfrdlMgT8GIRAikdzsQ.jpg",
                contentDescription = "Imagen de prueba",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Button(
                modifier = Modifier,
                onClick = onBackHome
            ) {
                Text(
                    text = "Regresar",
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}