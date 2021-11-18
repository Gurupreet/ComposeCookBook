package com.guru.composecookbook.ui.home.customfling

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.ComposeCookBookTheme

/**
 * @author https://github.com/iamjosephmj
 */
@Composable
fun VerticalFlingerListItem(item: Item, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        val imageModifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))

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
fun PreviewVerticalFlingerListItem() {
    val item = DemoDataProvider.item
    ComposeCookBookTheme {
        VerticalFlingerListItem(item = item)
    }
}