package com.guru.composecookbook.cryptoapp.ui.home.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.guru.composecookbook.cryptoapp.R
import com.guru.composecookbook.theme.gradientBluePurple
import com.guru.composecookbook.theme.green500
import com.guru.composecookbook.theme.modifiers.horizontalGradientBackground
import com.guru.composecookbook.theme.typography

@Composable
fun MyWalletCard() {
    //This is dummy data for crypto wallet
    val paddingWithStatusBarHeight = 30.dp
    var extended by remember { mutableStateOf(false) }
    val animateHeight = if (extended) 500.dp else 200.dp
    val opacity = if (extended) 0.8f else 1f
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(animateDpAsState(animateHeight).value)
            .alpha(animateFloatAsState(opacity).value)
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .horizontalGradientBackground(gradientBluePurple)
            .padding(top = paddingWithStatusBarHeight, bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        val (title, image, price, asset, dailyChange, send, receive, scan) = createRefs()
        val horizontalCenterGuideline = createGuidelineFromStart(0.5f)
        Icon(
            painter = painterResource(id = R.drawable.ic_ethereum_brands),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .alpha(0.7f)
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(title.bottom, margin = 8.dp)
                }
        )
        Text(
            text = "My Wallet",
            style = typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "340.13 USD",
            style = typography.h6,
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(price) {
                    top.linkTo(title.bottom)
                    start.linkTo(image.end)
                }
        )
        Text(
            text = "1.23 ETH",
            textAlign = TextAlign.Right,
            style = typography.h6.copy(fontSize = 12.sp),
            modifier = Modifier
                .padding(start = 4.dp)
                .constrainAs(asset) {
                    top.linkTo(price.bottom)
                    start.linkTo(image.end)
                }
        )
        Text(
            text = "24H: +1.23%",
            color = green500,
            style = typography.body2.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(dailyChange) {
                    end.linkTo(parent.end)
                    top.linkTo(price.top)
                }
        )
        ExtendedFloatingActionButton(
            onClick = { extended = !extended },
            modifier = Modifier
                .padding(4.dp)
                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                .constrainAs(receive) {
                    start.linkTo(parent.start)
                    end.linkTo(horizontalCenterGuideline)
                    top.linkTo(asset.bottom, margin = 16.dp)
                },
            icon = {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface
                )
            },
            text = { Text(text = "Receive") },
            backgroundColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation()
        )
        ExtendedFloatingActionButton(
            onClick = { extended = !extended },
            modifier = Modifier
                .padding(4.dp)
                .border(1.dp, Color.White, RoundedCornerShape(8.dp))
                .constrainAs(send) {
                    start.linkTo(horizontalCenterGuideline)
                    end.linkTo(parent.end)
                    top.linkTo(asset.bottom, margin = 16.dp)
                },
            icon = { Icon(imageVector = Icons.Default.Send, contentDescription = null) },
            text = { Text(text = "Send") },
            backgroundColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation()
        )
        Image(
            imageVector = Icons.Default.QrCodeScanner,
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .alpha(animateFloatAsState(if (extended) 1f else 0f).value)
                .constrainAs(scan) {
                    top.linkTo(receive.bottom, margin = 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Preview
@Composable
fun PreviewWalletCard() {
    MyWalletCard()
}