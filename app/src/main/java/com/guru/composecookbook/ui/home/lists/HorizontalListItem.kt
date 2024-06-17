package com.guru.composecookbook.ui.home.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.components.Material3Card
import com.guru.composecookbook.ui.utils.TestTags

/**
 * Composable function to display a horizontal list item.
 *
 * @param item The item to display, which contains information such as title, subtitle, source, and image.
 * @param modifier The modifier to be applied to the layout of the grid list item.
 */
@Composable
fun HorizontalListItem(
    item: Item,
    modifier: Modifier = Modifier
) {

    Material3Card(
        shape = androidx.compose.material.MaterialTheme.shapes.medium,
        modifier = modifier
            .size(280.dp, 200.dp)
            .testTag("${TestTags.HOME_SCREEN_LIST_ITEM}-${item.id}"),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.clickable(onClick = { })) {
            Image(
                painter = painterResource(id = item.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.subtitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = item.source,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

/**
 * Preview function for HorizontalListItem composable.
 */
@Preview
@Composable
fun PreviewHorizontalListItem() {
    val item = DemoDataProvider.item
    ComposeCookBookTheme {
        HorizontalListItem(item = item)
    }
}