package com.guru.composecookbook.meditation.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.guru.composecookbook.meditation.ui.spacerHeight10
import com.guru.composecookbook.meditation.ui.spacerHeight20
import com.guru.composecookbook.meditation.R
import com.guru.composecookbook.meditation.ui.component.BottomMenuItem
import com.guru.composecookbook.meditation.ui.component.CategoryItem
import com.guru.composecookbook.meditation.ui.component.DailyThoughtsItem
import com.guru.composecookbook.meditation.ui.component.FeatureItem
import com.guru.composecookbook.meditation.ui.component.RecommendationItem
import com.guru.composecookbook.meditation.ui.component.TopicItem
import com.guru.composecookbook.meditation.ui.model.BottomMenuContent
import com.guru.composecookbook.meditation.ui.model.Categories
import com.guru.composecookbook.meditation.ui.model.DailyThought
import com.guru.composecookbook.meditation.ui.model.Feature
import com.guru.composecookbook.meditation.ui.model.Recommendation
import com.guru.composecookbook.meditation.ui.model.Topic
import com.guru.composecookbook.meditation.ui.model.bottomList
import com.guru.composecookbook.meditation.ui.model.categoryList
import com.guru.composecookbook.meditation.ui.model.recommendationList
import com.guru.composecookbook.meditation.ui.model.topicList
import com.guru.composecookbook.meditation.ui.theme.Blue
import com.guru.composecookbook.meditation.ui.theme.DeepBlue
import com.guru.composecookbook.meditation.ui.theme.Gray
import com.guru.composecookbook.meditation.ui.theme.Orange
import com.guru.composecookbook.meditation.ui.theme.TextWhite
import com.guru.composecookbook.meditation.ui.theme.dp10
import com.guru.composecookbook.verticalgrid.StaggeredVerticalGrid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun MeditationHome() {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(bottom = 100.dp)

        ) {
            item {
                appTitle()
                spacerHeight10()
                greetingSection(name = stringResource(R.string.person_name))
                spacerHeight20()
                CategoriesSection(
                    categoryList
                )
                spacerHeight20()
                FeatureSection()
                spacerHeight20()
                DailyThoughtsSection()
                spacerHeight20()
                RecommendationSection(
                    recommendationList
                )
                spacerHeight20()
                TopicSection(
                    topicList
                )
            }
        }

        BottomMenu(
            items = bottomList, modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun appTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.silent),
            style = MaterialTheme.typography.h1,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = DeepBlue
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "App Icon"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(R.string.moon),
            style = MaterialTheme.typography.h1,
            fontSize = 16.sp,
            color = DeepBlue
        )
    }
}

@Composable
fun greetingSection(
    modifier: Modifier = Modifier.wrapContentHeight(),
    name: String = stringResource(R.string.your_name)
) {
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.good_morning) + " $name",
            style = MaterialTheme.typography.h1,
            fontSize = 22.sp
        )
        Text(
            text = stringResource(R.string.wishing_desc),
            style = MaterialTheme.typography.body1,
            color = Gray,
            fontSize = 18.sp
        )
    }
}

@Composable
fun FeatureSection() {
    Row(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        FeatureItem(
            Feature(stringResource(R.string.basic), description = stringResource(R.string.course), R.drawable.ic_feature_1, stringResource(
                            R.string.feature_time)
                        ),
            Modifier
                .weight(1f)
                .padding(end = 7.5.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(dp10))
                .background(Blue)
                .wrapContentHeight(), TextWhite
        )
        FeatureItem(
            Feature(stringResource(R.string.relaxation), stringResource(R.string.music), R.drawable.ic_feature_2, stringResource(
                R.string.feature_time)), Modifier
                .weight(1f)
                .padding(start = 7.5.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(dp10))
                .background(Orange)
                .wrapContentHeight(), DeepBlue
        )
    }
}

@Composable
fun DailyThoughtsSection() {
    Box(modifier = Modifier.padding(horizontal = 15.dp)) {
        DailyThoughtsItem(
            DailyThought(
                stringResource(R.string.daily_thoughts),
                stringResource(R.string.meditation_time),
                TextWhite
            )
        )
    }

}

@Composable
fun RecommendationSection(recommendationList: List<Recommendation>) {
    Column {
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = stringResource(R.string.recommendations),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Start,
            fontSize = 20.sp
        )
        spacerHeight10()
        LazyRow(
            contentPadding = PaddingValues(horizontal = 7.dp)
        ) {
            items(recommendationList.size) {
                RecommendationItem(recommendation = recommendationList[it])
            }
        }
    }
}

@Composable
fun CategoriesSection(categoriesList: List<Categories>, initialSelectedItemIndex: Int = 0) {

    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    LazyRow(
        modifier = Modifier.padding(start = 15.dp)
    ) {
        items(categoriesList.size) {
            CategoryItem(category = categoriesList[it], selectedItem = selectedItemIndex == it) {
                selectedItemIndex = it
            }
        }
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = Blue,
    activeTextColor: Color = Blue,
    inactiveTextColor: Color = Gray,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Box(modifier = modifier.shadow(elevation = 8.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(White)
                .padding(15.dp)
                .height(80.dp)
        ) {
            items.forEachIndexed { index, item ->
                BottomMenuItem(
                    item = item,
                    isSelected = index == selectedItemIndex,
                    activeHighlightColor = activeHighlightColor,
                    activeTextColor = activeTextColor,
                    inactiveTextColor = inactiveTextColor
                ) {
                    selectedItemIndex = index
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun TopicSection(topicList: List<Topic>) {
    val scope = rememberCoroutineScope()
    val state = rememberLazyListState()
    state.disableScrolling(scope)
    Column(
        modifier = Modifier.padding(horizontal = 7.5.dp)
    ) {
        Text(
            text = stringResource(R.string.topics),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 7.5.dp)
        )
        spacerHeight10()
        val itemSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2) - 23.dp
            StaggeredVerticalGrid(maxColumnWidth = 250.dp){
                topicList.forEachIndexed { index, _ ->
                    TopicItem(topic = topicList[index], itemSize)
                }
            }
    }

}

fun LazyListState.disableScrolling(scope: CoroutineScope) {
    scope.launch {
        scroll(scrollPriority = MutatePriority.PreventUserInput) {
            awaitCancellation()
        }
    }
}