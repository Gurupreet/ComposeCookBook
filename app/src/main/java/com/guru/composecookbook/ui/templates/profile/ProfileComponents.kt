package com.guru.composecookbook.ui.templates.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.green700
import com.guru.composecookbook.theme.typography

@Composable
fun MyPhotosSection() {
    Text(
        text = "My Photography",
        style = typography.h6,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    val imageModifier = Modifier
        .padding(vertical = 8.dp, horizontal = 4.dp)
        .preferredSize(120.dp)
        .clip(RoundedCornerShape(8.dp))

    Row(
        modifier = Modifier.padding(start = 8.dp, top = 8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            bitmap = imageResource(id = R.drawable.food2),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Image(
            bitmap = imageResource(id = R.drawable.food3),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            bitmap = imageResource(id = R.drawable.food6),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
    Row(
        modifier = Modifier.padding(start = 8.dp, top = 8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            bitmap = imageResource(id = R.drawable.food12),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            bitmap = imageResource(id = R.drawable.food13),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            bitmap = imageResource(id = R.drawable.food15),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun InterestTag(text: String) {
    val tagModifier = Modifier
        .padding(4.dp)
        .clickable(onClick = {})
        .clip(RoundedCornerShape(4.dp))
        .background(green200.copy(alpha = 0.2f))
        .padding(horizontal = 8.dp, vertical = 4.dp)

    Text(
        text = text,
        color = green700,
        modifier = tagModifier,
        style = typography.body2.copy(fontWeight = FontWeight.Bold)
    )
}
