package com.guru.composecookbook.ui.home.customfling

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.data.model.Item
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.utils.TestTags

/**
 * This Activity renders the List to Showcase the fling behaviour.
 *
 * @author https://github.com/iamjosephmj
 */
class FlingListActivity : ComponentActivity() {

    // This variable is used to update the tree with the latest values.
    private val flingStateStore: MutableLiveData<FlingStateStore> by lazy {
        MutableLiveData(FlingStateStore.INSTANCE.copy())
    }

    // Default Light/Dark selection from previous activity.
    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startContentRendering(DemoDataProvider.itemList)
    }

    /**
     * This method renders the list along with its fling behaviour.
     *
     */
    private fun startContentRendering(list: List<Item>) {
        setContent {
            BaseView(isDarkTheme) {

                // This updates the complete compose tree.
                val flingState = flingStateStore.observeAsState(FlingStateStore.INSTANCE.copy())

                ListViewContent(list, flingState,
                    {
                        /*
                         * To handle onBackPressed in the activity, This will redirect the user
                         * to the previous page.
                         */
                        onBackPressed()
                    }) {
                    // Opens the settings activity.
                    openSettings()
                }
            }
        }
    }

    /*
     * This variable handles onActivity result.
    */
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode != Activity.RESULT_OK) {
                Toast.makeText(
                    this,
                    "Your settings was not saved, please click on apply in the settings page to test the custom fling behaviour",
                    Toast.LENGTH_SHORT
                ).show()
            }
            //Updating the UI based on the latest settings from the `FlingListSettingsActivity`.
            flingStateStore.value = FlingStateStore.INSTANCE.copy()
        }


    companion object {
        const val DARK_THEME = "darkTheme"

        /**
         * Starts the {@see FlingListActivity} with current theme as {@see Intent} data.
         */
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, FlingListActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }

    /**
     * This function opens the {@see FlingListSettingsActivity}
     */
    private fun openSettings() {
        // making intent with current theme.
        val intent = FlingListSettingsActivity.newIntent(context = this, isDarkTheme = isDarkTheme)
        startForResult.launch(intent)

        // Resetting the present UI state to FlingListViewTypes.SMOOTH
        val resetFling = flingStateStore.value?.copy(type = FlingListViewTypes.SMOOTH)
        flingStateStore.value = resetFling

    }

}

@Composable
private fun BaseView(isDarkTheme: Boolean, content: @Composable () -> Unit) {
    ComposeCookBookTheme(isDarkTheme) {
        content()
    }
}

@Composable
fun ListViewContent(
    list: List<Item>,
    flingStateStore: State<FlingStateStore>,
    onback: () -> Unit,
    onSettingsClick: () -> Unit
) {
    /*
     * This is done to match up the designs of the present page with the other pages of the App.
     */
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(4.dp)) {
                        /*
                         * Presently I am setting the heading as `Custom Fling`.
                         * @gurupreet, please let me know If I need to change this.
                         */
                        Text(
                            text = "Custom Fling",
                            modifier = Modifier.testTag(TestTags.HOME_FLING_HEADER)
                        )
                    }
                },
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = onback) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = {
            VerticalFlingerListView(list, flingStateStore) {
                onSettingsClick()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeCookBookTheme {
        val state =
            MutableLiveData(FlingStateStore.INSTANCE.copy()).observeAsState(FlingStateStore.INSTANCE.copy())

        ListViewContent(
            DemoDataProvider.itemList, state,
            onback = {}) {}
    }
}