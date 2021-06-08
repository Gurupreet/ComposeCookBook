package com.guru.composecookbook.ui.home.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
            .clip(shape = MaterialTheme.shapes.medium)

        Image(
            painter = painterResource(item.imageId),
            modifier = imageModifier,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = item.title,
            style = typography.h6
        )
        Text(
            text = item.subtitle,
            style = typography.body2
        )

        Text(
            text = item.source,
            style = typography.subtitle2
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