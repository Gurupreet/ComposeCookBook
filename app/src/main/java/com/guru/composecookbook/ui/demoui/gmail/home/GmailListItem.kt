package com.guru.composecookbook.ui.demoui.gmail.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet


@Composable
fun GmailListItem(item: Tweet, clickListener: (Tweet) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .background(MaterialTheme.colors.background.copy(alpha = 0.6f))
            .fillMaxWidth()
            .clickable { clickListener(item) }
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        val (image, title, subtitle, source, button, time) = createRefs()
        createVerticalChain(title, subtitle, source, chainStyle = ChainStyle.Packed)

        Image(
            imageResource(id = item.authorImageId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .preferredSize(50.dp)
                .clip(CircleShape)
                .constrainAs(image) {
                    linkTo(start = parent.start, end = title.start)
                    top.linkTo(title.top)
                }
        )
        Text(
            text = item.author,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(image.end, 16.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = "5 sept",
            style = MaterialTheme.typography.h6.copy(fontSize = 12.sp),
            modifier = Modifier.constrainAs(time) {
                end.linkTo(parent.end, margin = 8.dp)
                top.linkTo(title.top)
            }
        )
        Text(
            text = item.source,
            style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
            modifier = Modifier.constrainAs(subtitle) {
                start.linkTo(image.end, 16.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = item.text,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(source) {
                linkTo(start = title.start, end = parent.end)
                width = Dimension.fillToConstraints
            }.drawOpacity(0.6f)
        )
        var stared by remember(item.id) { mutableStateOf(false) }
        IconButton(
            onClick = { stared = !stared },
            modifier = Modifier
                .constrainAs(button) {
                    linkTo(
                        top = source.top,
                        bottom = source.bottom,
                    )
                    linkTo(
                        start = source.end,
                        end = parent.end,
                        endMargin = 16.dp
                    )
                }
        ){
            Icon(
                if (stared) Icons.Default.Star else Icons.Default.StarBorder,
                modifier = Modifier.padding(end = 12.dp),
                tint = if (stared) Color.Yellow else MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
@Preview
fun PreviewGmailItem() {
    val item = DemoDataProvider.tweet
    GmailListItem(item = item) {

    }
}