package com.guru.composecookbook.ui.home.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.ComposeCookBookTheme

@Composable
fun VerticalListItemSmall(item: Item, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Row(
        modifier = Modifier
            .clickable(onClick = { })
            .padding(16.dp)
    ) {
        ItemImage(
            item,
            Modifier.padding(end = 16.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(item.title, style = typography.subtitle1)
            Text(item.subtitle, style = typography.body2)
        }
        FavIcon(modifier)
    }
}

@Composable
fun ItemImage(item: Item, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = item.imageId),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .size(100.dp, 80.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun FavIcon(modifier: Modifier = Modifier) {
    val isFavourite = remember { mutableStateOf(true) }
    IconToggleButton(
        checked = isFavourite.value,
        onCheckedChange = { isFavourite.value = !isFavourite.value }
    ) {
        if (isFavourite.value) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = modifier
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}


@Preview
@Composable
fun PreviewListViewItemSmall() {
    val item = DemoDataProvider.item
    ComposeCookBookTheme {
        VerticalListItemSmall(item)
    }
}
