package com.pdmcourse2026.RankeUca.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pdm.rankeuca.domain.models.Option

@Composable
fun OptionCard(
    option : Option,
    onPlaceClick: (id: Int) -> Unit,
    isVoteScreen: Boolean = false
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPlaceClick(option.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults
            .elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row() {
            AsyncImage(
                model = option.imageUrl,
                contentDescription = option.value,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column() {
                Text(text = option.value)
                if(isVoteScreen) {
                    Text(text = option.votes.toString())
                }
                else {
                    Text(text = "Toca para votar")
                }

            }
        }
    }
}