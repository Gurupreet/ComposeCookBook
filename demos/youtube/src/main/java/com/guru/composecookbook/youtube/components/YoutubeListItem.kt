package com.guru.composecookbook.youtube.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Tweet
import com.guru.composecookbook.youtube.R

@Composable
fun YoutubeListItem(item: Tweet) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        val (image, authorImage, title, subtitle, button) = createRefs()

        Image(
            painter = painterResource(id = item.tweetImageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .constrainAs(image) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    width = Dimension.fillToConstraints
                }
        )
        Image(
            painter = painterResource(id = R.drawable.p3),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .constrainAs(authorImage) {
                    start.linkTo(parent.start, margin = 12.dp)
                    top.linkTo(image.bottom, margin = 16.dp)
                    end.linkTo(title.start)
                }
        )
        Text(
            text = item.text,
            style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(title) {
                linkTo(
                    start = authorImage.end,
                    startMargin = 16.dp,
                    end = button.start,
                    endMargin = 16.dp
                )
                linkTo(
                    top = authorImage.top,
                    bottom = subtitle.top
                )
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = "${item.author} . ${item.likesCount}k views . 6 hours ago",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .constrainAs(subtitle) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(title.start)
                    width = Dimension.fillToConstraints
                }
                .padding(bottom = 24.dp)
        )
        IconButton(
            onClick = { },
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(image.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            Icon(Icons.Default.MoreVert, tint = Color.Gray, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PreviewYoutubeListItem() {
    val item = DemoDataProvider.tweet
    YoutubeListItem(item = item)
}