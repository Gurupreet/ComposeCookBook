package com.guru.composecookbook.ui.cryptoappmvvm.ui.home

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.gradientBluePurple
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

@Composable
fun MyWalletCard() {
    val paddingWithStatusBarHeight = 40.dp
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .preferredHeight(200.dp)
            .clip(RoundedCornerShape(bottomLeft = 24.dp, bottomRight = 24.dp))
            .horizontalGradientBackground(gradientBluePurple)
            .padding(top = paddingWithStatusBarHeight, bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        val (title, price, dailyChange, address) = createRefs()
        Text(
            text = "My Portfolio",
            style = typography.body1,
            modifier = Modifier.padding(4.dp).constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "$300.13",
            style = typography.h6,
            modifier = Modifier.padding(4.dp).constrainAs(price) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "24H: -1.23%",
            style = typography.body2.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(4.dp).constrainAs(dailyChange) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )
    }
}

@Preview
@Composable
fun PreviewWalletCard() {
    MyWalletCard()
}