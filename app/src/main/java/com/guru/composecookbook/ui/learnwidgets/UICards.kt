package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.typography

@Composable
fun UICards() {
    Text(
        text = "UI Cards, Boxes and Lists",
        style = typography.h6,
        modifier = Modifier.padding(8.dp)
    )

    val item = remember { DemoDataProvider.item }

    Text(
        text = "Inbuilt box as container for any Clipping/Alignment controls",
        style = typography.subtitle1,
        modifier = Modifier.padding(8.dp)
    )
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(topLeft = 16.dp, bottomRight = 16.dp)
    ) {
        Column {
            Text(
                text = item.title,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = item.subtitle,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
    Divider()

    Text(text = "Inbuilt Card", style = typography.subtitle1, modifier = Modifier.padding(8.dp))
    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Row {
            Image(
                bitmap = imageResource(id = R.drawable.p3),
                contentDescription = null,
                modifier = Modifier.preferredSize(60.dp)
            )
            Text(text = item.title, modifier = Modifier.padding(16.dp))
        }
    }
    Divider()

    Text(
        text = "In-built ListItems",
        style = typography.subtitle1,
        modifier = Modifier.padding(8.dp)
    )
    ListItem(text = { Text(item.title) }, secondaryText = { Text(item.subtitle) })
    Divider(modifier = Modifier.padding(4.dp))
    ListItem(
        text = { Text(item.title) },
        secondaryText = { Text(item.subtitle) },
        singleLineSecondaryText = false
    )
    Divider(modifier = Modifier.padding(4.dp))
    ListItem(text = { Text(item.title) }, secondaryText = { Text(item.subtitle) }, icon = {
        Image(
            imageResource(R.drawable.p3),
            contentDescription = null,
        )
    })
    Divider(modifier = Modifier.padding(4.dp))
    //I am not sure why this is not going multiline for secondaryText...
    ListItem(
        text = { Text(item.title) },
        secondaryText = { Text(item.subtitle) },
        icon = { Image(imageResource(id = R.drawable.p1), contentDescription = null) },
        overlineText = { Text("Overline text") },
        singleLineSecondaryText = false
    )
    Divider()
    ListItem(
        text = { Text(item.title) },
        secondaryText = { Text(item.subtitle) },
        icon = { Image(imageResource(id = R.drawable.p2), contentDescription = null) },
        trailing = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
        singleLineSecondaryText = false
    )
    Divider()

}