package com.guru.composecookbook.carousel

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.typography

@Composable
fun CarouselLayout() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        val items = remember { DemoDataProvider.itemList.take(10) }
        val pagerState: PagerState = run {
            remember { PagerState(2, 0, items.size - 1) }
        }
        val selectedPage = remember { mutableStateOf(2) }

        Pager(state = pagerState, modifier = Modifier.height(200.dp)) {
            val item = items[page]
            selectedPage.value = pagerState.currentPage
            CarouselItem(item)
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            items.forEachIndexed { index, _ ->
                CarouselDot(
                    selected = index == selectedPage.value,
                    MaterialTheme.colors.primary,
                    Icons.Filled.Lens
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        //Pager 2
        Pager(state = pagerState, modifier = Modifier.height(200.dp)) {
            val item = items[page]
            selectedPage.value = pagerState.currentPage
            CarouselItemCircle(item)
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            items.forEachIndexed { index, _ ->
                CarouselDot(
                    selected = index == selectedPage.value,
                    MaterialTheme.colors.error,
                    Icons.Filled.Favorite
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        //Pager 3
        Pager(state = pagerState, modifier = Modifier.height(350.dp)) {
            val item = items[page]
            selectedPage.value = pagerState.currentPage
            CarouselItemCard(item, pagerState, selectedPage)
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            items.forEachIndexed { index, _ ->
                CarouselDot(
                    selected = index == selectedPage.value,
                    MaterialTheme.colors.secondary,
                    Icons.Filled.Album
                )
            }
        }
    }
}

@Composable
fun CarouselDot(selected: Boolean, color: Color, icon: ImageVector) {
    Icon(
        imageVector = icon,
        modifier = Modifier
            .padding(4.dp)
            .size(12.dp),
        contentDescription = null,
        tint = if (selected) color else Color.Gray
    )
}

@Composable
fun CarouselItem(item: Item) {
    Box {
        Image(
            painter = painterResource(id = item.imageId),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = item.title,
            style = typography.h6.copy(color = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.BottomStart),
        )
    }
}

@Composable
fun CarouselItemCircle(item: Item) {
    Image(
        painter = painterResource(id = item.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(16.dp)
            .size(160.dp)
            .clip(CircleShape)
    )
}

@Composable
fun CarouselItemCard(item: Item, pagerState: PagerState, selectedPage: MutableState<Int>) {
    val isSelected = selectedPage.value == pagerState.currentPage
    val animateSize = if (isSelected) 320.dp else 180.dp
    val animateElevation = if (isSelected) 12.dp else 2.dp
    Card(
        elevation = animateDpAsState(animateElevation).value,
        modifier = Modifier
            .size(animateDpAsState(animateSize).value)
            .padding(24.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = green200,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Column {
            Text(
                text = item.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                style = typography.body2
            )
            Image(
                painter = painterResource(id = item.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    }

}


@Preview
@Composable
fun PreviewCarouselLayout() {
    CarouselLayout()
}

@Preview
@Composable
fun PreviewCarouselItem() {
    val item = DemoDataProvider.item
    CarouselItem(item = item)
}

@Preview
@Composable
fun PreviewCarouselItemCircle() {
    val item = DemoDataProvider.item
    CarouselItemCircle(item = item)
}
