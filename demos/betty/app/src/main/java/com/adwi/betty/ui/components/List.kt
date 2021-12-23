package com.adwi.betty.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adwi.betty.R
import com.adwi.betty.data.local.entity.Odd
import com.adwi.betty.ui.main.Range
import com.adwi.betty.ui.theme.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalFoundationApi::class)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BettyOddList(
    progress: Float,
    onRefresh: () -> Unit,
    list: List<Odd>,
    modifier: Modifier
) {
    val showRefresh = false
//        (list.isEmpty() && progress < .97)

    val swipeRefreshState = rememberSwipeRefreshState(showRefresh)
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    ).value

    Box(modifier) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = onRefresh,
            modifier = modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 80.dp),
                modifier = Modifier.fillMaxWidth()
            ) {


                val grouped = list.groupBy { it.range.name }

                grouped.forEach { (value, oddList) ->
                    stickyHeader {

                        val backgroundColor = when (value) {
                            Range.LOW.name -> LowRange
                            Range.MEDIUM.name -> MediumRange
                            else -> HighRange
                        }

                        ListHeader(
                            name = value,
                            listSize = oddList.size,
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .background(backgroundColor),
                        )
                    }
                    items(oddList) { odd ->
                        BettyOddListItem(odd = odd)
                    }
                }
            }

            val showNoData = (list.isNullOrEmpty() && progress > .97)
            AnimatedVisibility(
                visible = showNoData,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                AnimatedView(resource = R.raw.no_results, modifier = Modifier.fillMaxWidth().height(200.dp))
//
//                Text(text = "No results", color = MaterialTheme.colors.onBackground)
            }

            val showLoadingResults = (list.isNullOrEmpty() && progress < .97)
            AnimatedVisibility(
                visible = showLoadingResults,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                AnimatedView(resource = R.raw.loading, modifier = Modifier)
//                Text(text = "Loading results", color = MaterialTheme.colors.onBackground)
            }

            val showButton = listState.firstVisibleItemIndex > 0
            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                ScrollToTopButton(
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(index = 0)
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                )
            }

            val showProgress = progress < .97
            AnimatedVisibility(visible = showProgress) {
                LinearProgressIndicator(
                    progress = animatedProgress,
                    color = MaterialTheme.colors.secondary,
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
fun ScrollToTopButton(onClick: () -> Unit, modifier: Modifier) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.ArrowUpward,
            contentDescription = "Scroll to top",
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun ListHeader(name: String, listSize: Int, modifier: Modifier) {
    Row(modifier = modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
        Text(text = name, color = Color.Black)
        Text(text = "Total: $listSize", color = Color.Black)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BettyOddListItem(
    odd: Odd
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {
        Row() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
            ) {
                ListItemInfoPanel(
                    odd = odd,
                    modifier = Modifier.fillMaxWidth()
                )
                ListItemTeamsPanel(odd)
                ListItemTimeInfoPanel(odd)
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .height(1.dp)
                .background(LightGray)
        )
    }
}

@Composable
fun ListItemInfoPanel(

    odd: Odd,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = odd.bookmakerTitle,
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = odd.sportName,
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ListItemTeamsPanel(odd: Odd) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = odd.teamAName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = odd.teamAPrice.toString())
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "|", modifier = Modifier.rotate(90F))
            Text(
                text = odd.difference.toString(),
                color = MaterialTheme.colors.onSurface,
                fontSize = 12.sp
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = odd.teamBName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = odd.teamBPrice.toString())
        }
    }
}

@Composable
fun ListItemTimeInfoPanel(odd: Odd) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Text(
            text = odd.commenceTime,
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
    }
}

// PREVIEWS ----------------------------------------------------------------------------------

@OptIn(ExperimentalMaterialApi::class)
@Preview(name = "BettyOddListItem", showBackground = true)
@Composable
fun BettyOddListItemPreview() {
    BettyOddListItem(odd = Odd.mock)
}

@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalFoundationApi::class)
@OptIn(ExperimentalMaterialApi::class)
@Preview(name = "BettyOddList", showBackground = true)
@Composable
fun BettyOddListPreview() {
    Column(modifier = Modifier.background(LightGray)) {
        BettyOddList(
            progress = .3f,
            onRefresh = {},
            list = Odd.mockList,
            modifier = Modifier
        )
    }
}