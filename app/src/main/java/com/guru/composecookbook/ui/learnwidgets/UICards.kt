package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.components.Material3Card
import com.guru.composecookbook.theme.typography

@OptIn(ExperimentalMaterialApi::class)
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
    Material3Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
    ) {
        Column {
            Text(
                text = item.title,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = item.subtitle,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
    Divider()

    Text(text = "Inbuilt Card", style = typography.subtitle1, modifier = Modifier.padding(8.dp))
    Material3Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.p3),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
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
            painter = painterResource(R.drawable.p3),
            contentDescription = null,
        )
    })
    Divider(modifier = Modifier.padding(4.dp))
    //I am not sure why this is not going multiline for secondaryText...
    ListItem(
        text = { Text(item.title) },
        secondaryText = { Text(item.subtitle) },
        icon = { Image(painter = painterResource(id = R.drawable.p1), contentDescription = null) },
        overlineText = { Text("Overline text") },
        singleLineSecondaryText = false
    )
    Divider()
    ListItem(
        text = { Text(item.title) },
        secondaryText = { Text(item.subtitle) },
        icon = { Image(painter = painterResource(id = R.drawable.p2), contentDescription = null) },
        trailing = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
        singleLineSecondaryText = false
    )
    Divider()

}