package com.guru.composecookbook.meditation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.meditation.ui.model.Categories
import com.guru.composecookbook.meditation.ui.spacerHeight5
import com.guru.composecookbook.meditation.ui.theme.Blue
import com.guru.composecookbook.meditation.ui.theme.DeepBlue
import com.guru.composecookbook.meditation.ui.theme.Gray
import com.guru.composecookbook.meditation.ui.theme.TextWhite
import org.w3c.dom.Text

@Composable
fun CategoryItem(
    category: Categories,
    selectedItem : Boolean,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(end = 15.dp)

    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(color = if (selectedItem) Blue else Gray)
                .clickable {
                    onItemClick()
                }
        ) {
            Icon(
                painter = painterResource(id = category.icon),
                contentDescription = "Category Icon",
                tint = TextWhite,
                modifier = Modifier.padding(18.dp).size(20.dp)
            )
        }
        spacerHeight5()
        Text(
            text = category.title,
            style = MaterialTheme.typography.body1,
            fontSize = 12.sp,
            color = if (selectedItem) DeepBlue else Gray,
            fontWeight = FontWeight.Bold
        )
    }
}