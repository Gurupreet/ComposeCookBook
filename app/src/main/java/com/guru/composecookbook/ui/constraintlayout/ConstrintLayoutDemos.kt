package com.guru.composecookbook.ui.constraintlayout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.ui.cryptoappmvvm.ui.home.components.MyWalletCard
import com.guru.composecookbook.ui.demoui.gmail.GmailListItem
import com.guru.composecookbook.ui.demoui.youtube.YoutubeListItem

@Composable
fun ConstraintLayoutDemos() {
    ScrollableColumn(modifier = Modifier.padding(8.dp)) {
        ConstraintLayoutListItem()
        Spacer(modifier = Modifier.height(20.dp))
        ConstraintLayoutBigListItem()
        Spacer(modifier = Modifier.height(20.dp))
        val tweet = DemoDataProvider.tweet
        GmailListItem(tweet)
        Spacer(modifier = Modifier.height(20.dp))
        val tweet2 = DemoDataProvider.tweetList.filter { it.tweetImageId > 0 }.first()
        YoutubeListItem(tweet2)
        Spacer(modifier = Modifier.height(20.dp))
        MyWalletCard()
    }
}


@Composable
fun ConstraintLayoutListItem() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(12.dp)
    ) {
        val item = remember { DemoDataProvider.item }
        val (image, title, subtitle, space, source, button) = createRefs()
        createVerticalChain(title, subtitle, space, source, chainStyle = ChainStyle.Packed)

        Image(
            asset = imageResource(id = item.imageId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .preferredSize(120.dp)
                .clip(CircleShape)
                .constrainAs(image) {
                    linkTo(
                        top = parent.top,
                        topMargin = 16.dp,
                        bottom = parent.bottom,
                        bottomMargin = 16.dp
                    )
                    linkTo(start = parent.start, end = title.start)
                }
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.constrainAs(title) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = button.start,
                    endMargin = 16.dp
                )
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = item.subtitle,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.constrainAs(subtitle) {
                linkTo(start = title.start, end = parent.end)
                width = Dimension.fillToConstraints
            }
        )
        Spacer(
            Modifier
                .preferredHeight(8.dp)
                .constrainAs(space) {
                    linkTo(top = subtitle.bottom, bottom = source.top)
                }
        )
        Text(
            text = item.source,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(source) {
                linkTo(start = title.start, end = parent.end)
                width = Dimension.fillToConstraints
            }
        )
        IconButton(
            onClick = { },
            icon = { Icon(Icons.Default.FavoriteBorder) },
            modifier = Modifier
                .constrainAs(button) {
                    linkTo(
                        top = title.top,
                        bottom = title.bottom
                    )
                    linkTo(
                        start = title.end,
                        end = parent.end,
                        endMargin = 8.dp
                    )
                }
        )
    }
}

@Composable
fun ConstraintLayoutBigListItem() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
    ) {
        val item = remember { DemoDataProvider.item }
        val (image, title, subtitle, source, button) = createRefs()
        createVerticalChain(title, subtitle, source, chainStyle = ChainStyle.Packed)

        Image(
            asset = imageResource(id = item.imageId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .preferredHeight(200.dp)
                .constrainAs(image) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    width = Dimension.fillToConstraints
                }
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.constrainAs(title) {
                linkTo(
                    start = parent.start,
                    startMargin = 16.dp,
                    end = parent.end,
                    endMargin = 16.dp
                )
                linkTo(
                    top = image.bottom,
                    topMargin = 8.dp,
                    bottom = subtitle.top
                )
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = item.subtitle,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.constrainAs(subtitle) {
                linkTo(start = title.start, end = title.end)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = item.source,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(bottom = 12.dp).constrainAs(source) {
                linkTo(start = title.start, end = title.end)
                linkTo(top = subtitle.bottom, bottom = parent.bottom)
                width = Dimension.fillToConstraints
            }
        )
        IconButton(
            onClick = { },
            icon = { Icon(Icons.Default.FavoriteBorder) },
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(parent.top, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        )
    }
}



@Preview
@Composable
fun showConstraintLayoutListItem() {
    ConstraintLayoutListItem()
}

@Preview
@Composable
fun showConstraintLayoutBigListItem() {
    ConstraintLayoutBigListItem()
}

