package com.guru.composecookbook.ui.home.constraintlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.guru.composecookbook.cryptoapp.ui.home.components.MyWalletCard
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.gmail.ui.home.GmailListItem
import com.guru.composecookbook.youtube.components.YoutubeListItem

@Composable
fun ConstraintLayoutDemos() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        ConstraintLayoutListItem()
        Spacer(modifier = Modifier.height(20.dp))
        ConstraintLayoutBigListItem()
        Spacer(modifier = Modifier.height(20.dp))
        val tweet = DemoDataProvider.tweet
        GmailListItem(tweet) {}
        Spacer(modifier = Modifier.height(20.dp))
        val tweet2 = DemoDataProvider.tweetList.first { it.tweetImageId > 0 }
        YoutubeListItem(tweet2)
        Spacer(modifier = Modifier.height(20.dp))
        MyWalletCard()
        Spacer(modifier = Modifier.height(400.dp))
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
            painter = painterResource(id = item.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
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
                .height(8.dp)
                .constrainAs(space) {
                    linkTo(top = subtitle.bottom, bottom = source.top)
                }
        )
        Text(
            text = item.source,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(source) {
                start.linkTo(title.start)
                width = Dimension.fillToConstraints
            }
        )
        IconButton(
            onClick = { },
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
        ) {
            Icon(Icons.Default.FavoriteBorder, contentDescription = null)
        }
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
            painter = painterResource(id = item.imageId),
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
            modifier = Modifier
                .padding(bottom = 12.dp)
                .constrainAs(source) {
                    start.linkTo(title.start)
                    linkTo(top = subtitle.bottom, bottom = parent.bottom)
                    width = Dimension.fillToConstraints
                }
        )
        IconButton(
            onClick = { },
            modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(parent.top, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        ) {
            Icon(Icons.Default.FavoriteBorder, contentDescription = null)
        }
    }
}


@Preview
@Composable
fun ShowConstraintLayoutListItem() {
    ConstraintLayoutListItem()
}

@Preview
@Composable
fun ShowConstraintLayoutBigListItem() {
    ConstraintLayoutBigListItem()
}

