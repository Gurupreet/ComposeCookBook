package com.guru.composecookbook.ui.home.customfling

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.ui.utils.TestTags

/**
 * This Activity renders the Settings page.
 *
 * @author https://github.com/iamjosephmj
 */
class FlingListSettingsActivity : ComponentActivity() {

    // Default Light/Dark selection from previous activity.
    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseView(isDarkTheme) {
                SettingsContent({
                    /*
                     * To handle onBackPressed in the activity, This will redirect the user
                     * to the previous page. The Previous page will be listening to the result
                     * from this activity.
                     */
                    finishWithStatus(Activity.RESULT_CANCELED)
                }) {
                    /*
                     * This is the onApply Click action. With this action,
                     * all the values set by the user are saved.
                     */
                    finishWithStatus(Activity.RESULT_OK)
                }
            }
        }
    }

    /**
     * This function stops the activity with a specific status.
     * @param status: This can be Activity.RESULT_OK or Activity.CANCEL
     */
    private fun finishWithStatus(status: Int) {
        setResult(status)
        finish()
    }

    companion object {
        const val DARK_THEME = "darkTheme"

        /**
         * Starts the {@see FlingListSettingsActivity} with current theme as {@see Intent} data.
         */
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, FlingListSettingsActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}


@Composable
fun SettingsContent(onback: () -> Unit, onApply: () -> Unit) {
    /*
    * This is done to match up the designs of the present page with the other pages of the App.
    */
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(4.dp)) {
                        /*
                        * Presently I am setting the heading as `Settings`.
                        * @gurupreet, please let me know If I need to change this.
                        */
                        Text(
                            text = "Settings",
                            modifier = Modifier.testTag(TestTags.HOME_FLING_SETTINGS_ROOT)
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
            RenderSettingsPage(onApply)
        })
}


@Composable
private fun BaseView(isDarkTheme: Boolean, content: @Composable () -> Unit) {
    ComposeCookBookTheme(isDarkTheme) {
        content()
    }
}