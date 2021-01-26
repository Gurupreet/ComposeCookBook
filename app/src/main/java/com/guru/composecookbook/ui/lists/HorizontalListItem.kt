package com.guru.composecookbook.ui.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.ComposeCookBookTheme

@Composable
fun HorizontalListItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.preferredSize(280.dp, 200.dp)
    ) {
        Column(modifier = Modifier.clickable(onClick = { })) {
            val image = imageResource(item.imageId)
            Image(
                image,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .preferredHeight(100.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.subtitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = item.source,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHorizontalListItem() {
    val item = DemoDataProvider.item
    ComposeCookBookTheme {
        HorizontalListItem(item = item)
    }
}