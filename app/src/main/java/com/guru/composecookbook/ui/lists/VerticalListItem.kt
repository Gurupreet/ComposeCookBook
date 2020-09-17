package com.guru.composecookbook.ui.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.ComposeCookBookTheme

@Composable
fun VerticalListItem(item: Item, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    val emphasisLevels = EmphasisAmbient.current

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        val imageModifier = Modifier
            .preferredHeightIn(150.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)

        Image(
            imageResource(item.imageId),
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.preferredHeight(16.dp))

        ProvideEmphasis(emphasisLevels.high) {
            Text(
                text = item.title,
                style = typography.h6
            )
            Text(
                text = item.subtitle,
                style = typography.body2
            )
        }

        ProvideEmphasis(emphasisLevels.medium) {
            Text(
                text = item.source,
                style = typography.body2
            )
        }
    }
}

@Preview
@Composable
fun PreviewVerticalListItem() {
    val item = DemoDataProvider.item
    ComposeCookBookTheme {
        VerticalListItem(item = item)
    }
}