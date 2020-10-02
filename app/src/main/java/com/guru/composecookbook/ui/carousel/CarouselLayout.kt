package com.guru.composecookbook.ui.carousel

import androidx.compose.animation.animate
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.green200
import com.guru.composecookbook.theme.typography

@Composable
fun CarouselLayout() {
    ScrollableColumn {
        val items = remember { DemoDataProvider.itemList.take(10) }
        val pagerState: PagerState = run {
            val clock = AnimationClockAmbient.current
            remember(clock) { PagerState(clock, 2, 0, items.size - 1) }
        }
        val selectedPage = remember { mutableStateOf(2) }
        //Pager 1
        //FIX: There is something wrong with selected page value so two dots are ahead always
        Pager(state = pagerState, modifier = Modifier.preferredHeight(200.dp)) {
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
        Pager(state = pagerState, modifier = Modifier.preferredHeight(200.dp)) {
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
        Pager(state = pagerState, modifier = Modifier.preferredHeight(500.dp)) {
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
fun CarouselDot(selected: Boolean, color: Color, icon: VectorAsset) {
    Icon(
        asset = icon,
        modifier = Modifier.padding(4.dp).preferredSize(12.dp),
        tint = if (selected) color else Color.Gray
    )
}

@Composable
fun CarouselItem(item: Item) {
    Stack {
        Image(
            asset = imageResource(id = item.imageId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth()
                .preferredHeight(180.dp).clip(RoundedCornerShape(12.dp))
        )
        Text(
            text = item.title,
            style = typography.h6.copy(color = Color.White),
            modifier = Modifier.fillMaxWidth().padding(24.dp)
                .align(Alignment.BottomStart),
        )
    }
}

@Composable
fun CarouselItemCircle(item: Item) {
    Image(
        asset = imageResource(id = item.imageId),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(16.dp)
            .preferredSize(160.dp).clip(CircleShape)
    )
}

@Composable
fun CarouselItemCard(item: Item, pagerState: PagerState, selectedPage: MutableState<Int>) {
    val isSelected = selectedPage.value == pagerState.currentPage
    val animateSize = if (isSelected) 320.dp else 180.dp
    val animateElevation = if (isSelected) 12.dp else 2.dp
    Card(
        elevation = animate(animateElevation),
        modifier = Modifier.preferredSize(animate(animateSize)).padding(24.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = green200,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        Column {
            Text(
                text = item.title,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                style = typography.body2
            )
            Image(
                asset = imageResource(id = item.imageId),
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(4.dp)
                    .align(Alignment.CenterHorizontally)
                    .preferredSize(100.dp).clip(CircleShape)
            )
        }
    }

}


@Preview
@Composable
fun previewCarouselLayout() {
    CarouselLayout()
}

@Preview
@Composable
fun previewCarouselItem() {
    val item = DemoDataProvider.item
    CarouselItem(item = item)
}

@Preview
@Composable
fun previewCarouselItemCircle() {
    val item = DemoDataProvider.item
    CarouselItemCircle(item = item)
}


@Preview
@Composable
fun previewCarouselItemCard() {
    val item = DemoDataProvider.item
    //CarouselItemCard(item = item, PagerState())
}