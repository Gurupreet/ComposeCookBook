package com.guru.composecookbook.ui.home.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.utils.TestTags

@Composable
fun VerticalListItem(item: Item, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("${TestTags.HOME_SCREEN_LIST_ITEM}-${item.id}")
    ) {

        val imageModifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .clip(shape = androidx.compose.material.MaterialTheme.shapes.medium)

        Image(
            painter = painterResource(item.imageId),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = item.title,
            style = typography.titleLarge
        )
        Text(
            text = item.subtitle,
            style = typography.bodyMedium
        )

        Text(
            text = item.source,
            style = typography.titleSmall
        )
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