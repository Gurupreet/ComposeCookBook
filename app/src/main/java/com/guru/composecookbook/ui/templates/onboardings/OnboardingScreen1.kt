package com.guru.composecookbook.ui.templates.onboardings

import android.animation.ValueAnimator
import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.guru.composecookbook.ui.home.carousel.Pager
import com.guru.composecookbook.ui.home.carousel.PagerState

@Composable
fun OnBoardingScreen1(onSkip: () -> Unit) {
    val pagerState: PagerState = run {
        remember {
            PagerState(0, 0, onboardingList.size - 1)
        }
    }
    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Pager(
                state = pagerState,
                orientation = Orientation.Horizontal,
                modifier = Modifier.fillMaxSize()
            ) {
                OnboardingPagerItem(page)
            }
            Text(
                text = "Skip",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(vertical = 48.dp, horizontal = 16.dp)
                    .clickable(onClick = onSkip)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 120.dp)
            ) {
                onboardingList.forEachIndexed { index, _ ->
                    OnboardingPagerSlide(
                        selected = index == pagerState.currentPage,
                        MaterialTheme.colors.primary,
                        Icons.Filled.Album
                    )
                }
            }
            Button(
                onClick = {
                    if (pagerState.currentPage != onboardingList.size - 1) pagerState.currentPage =
                        pagerState.currentPage + 1
                },
                modifier = Modifier
                    .animateContentSize()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
                    .height(50.dp)
                    .clip(CircleShape)
            ) {
                Text(
                    text = if (pagerState.currentPage == onboardingList.size - 1) "Let's Begin" else "Next",
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
        }
    }
}

@Composable
fun OnboardingPagerItem(page: Int) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieLoadingView(LocalContext.current, onboardingList[page].third)
        Text(
            text = onboardingList[page].first,
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = onboardingList[page].second,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun LottieLoadingView(context: Context, file: String) {
    val lottieView = remember {
        LottieAnimationView(context).apply {
            setAnimation(file)
            repeatCount = ValueAnimator.INFINITE
        }
    }
    AndroidView(
        { lottieView }, modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        it.playAnimation()
    }
}

@Composable
fun OnboardingPagerSlide(selected: Boolean, color: Color, icon: ImageVector) {
    Icon(
        imageVector = icon,
        modifier = Modifier.padding(4.dp),
        contentDescription = null,
        tint = if (selected) color else Color.Gray
    )
}

val onboardingList = listOf(
    Triple(
        "Team Collaborations",
        "Our tools help your teams collaborate for the best output results",
        "profile.json"
    ),
    Triple(
        "Improve Productivity",
        "Our tools are designed to improve productivity by automating all the stuff for you",
        "working.json"
    ),
    Triple(
        "Growth Tracking",
        "We provide dashboard and charts to track your growth easily and suggestions.",
        "food.json"
    )
)

