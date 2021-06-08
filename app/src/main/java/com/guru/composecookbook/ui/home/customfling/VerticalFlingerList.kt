package com.guru.composecookbook.ui.home.customfling

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.ui.utils.TestTags
import io.iamjosephmj.flinger.bahaviours.StockFlingBehaviours

/**
 * Renders the Settings button and LazyColumn.
 *
 * Takes in 3 parameters:
 * @param list : This is used to render the LazyColumn.
 * @param flingStateStore : updates the UI with latest fling behaviour.
 * @param onSettingsClick: Used to do a callback to the parent function that
 * launches the settings activity.
 *
 * @author https://github.com/iamjosephmj
 */
@Composable
fun VerticalFlingerListView(
    list: List<Item>,
    flingStateStore: State<FlingStateStore>,
    onSettingsClick: () -> Unit
) {
    Column {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag(TestTags.HOME_FLING_LIST_BUTTON)
        ) {

            // Settings button
            Button(
                onClick = {
                    // Click event callback.
                    onSettingsClick()
                },
                modifier = Modifier
                    .size(100.dp, 40.dp)
            ) {
                Text(
                    text = "Settings",
                    modifier = Modifier.testTag(TestTags.HOME_FLING_SETTINGS_BUTTON)
                )
            }
            RenderBottomList(list, flingStateStore)
        }
    }
}

/**
 * This method renders the list that is at the bottom of the `Setting` button.
 */
@Composable
fun RenderBottomList(list: List<Item>, flingBehavior: State<FlingStateStore>) {
    when (flingBehavior.value.type.name) {
        FlingListViewTypes.CUSTOM.name -> {
            RenderListWithBehaviour(list, flingBehavior.value.buildScrollBehaviour())
        }
        FlingListViewTypes.NATIVE.name -> {
            RenderListWithBehaviour(list, StockFlingBehaviours.getAndroidNativeScroll())
        }
        else -> {
            RenderListWithBehaviour(list, StockFlingBehaviours.smoothScroll())
        }
    }
}

@Composable
fun RenderListWithBehaviour(list: List<Item>, flingBehavior: FlingBehavior) {
    LazyColumn(
        // Custom Fling behaviour
        flingBehavior = flingBehavior,
        modifier = Modifier.testTag(TestTags.HOME_FLING_LIST)
    ) {
        items(
            items = list,
            itemContent = { item ->
                VerticalFlingerListItem(item = item)
                ListItemDivider()
            })
    }
}
