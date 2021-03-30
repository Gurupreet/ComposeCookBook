package com.guru.composecookbook.ui.home.dialogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.ComposeCookBookTheme
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity


class DialogsActivity : ComponentActivity() {

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookTheme(isDarkTheme) {
                DialogScreen(onBack = { onBackPressed() })
            }
        }
    }

    companion object {
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, isDarkTheme: Boolean) =
            Intent(context, DialogsActivity::class.java).apply {
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}

@Composable
fun DialogScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dialogs") },
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }

            )
        },
        content = {
            DialogsOptionList()
        }
    )
}

@Composable
fun DialogsOptionList() {
    //Here we are using power of making simple data classes act as stateful when using `by state`
    var dialogState by remember { mutableStateOf(DialogState(false, DialogType.SIMPLE)) }

    if (dialogState.showDialog) {
        //if state of show dialog changes to true it shows dialog passing state as false for dismiss
        ShowDialog(dialogState.dialogType) { dialogState = dialogState.copy(showDialog = false) }
    }

    // I am not sure why updating the `dialogState.showDialog = true` is not working. May be I am
    // missing something. I had to update whole state via copy
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Button(
            onClick = { dialogState = dialogState.copy(showDialog = true) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Plain Message Dialog")
        }
        Button(
            onClick = { dialogState = DialogState(true, DialogType.TITLE) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Title Dialog")
        }
        Button(
            onClick = { dialogState = DialogState(true, DialogType.VERTICALBUTTON) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Dialog with Vertical buttons")
        }
        Button(
            onClick = { dialogState = DialogState(true, DialogType.IMAGE) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Dialog with Image")
        }
        Button(
            onClick = { dialogState = DialogState(true, DialogType.LONGDIALOG) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Long Dialog")
        }
        Button(
            onClick = { dialogState = DialogState(true, DialogType.ROUNDED) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Extra Rounded Dialog")
        }
    }
}

@Composable
fun ShowDialog(type: DialogType, onDismiss: () -> Unit) {
    val item = remember { DemoDataProvider.item }

    when (type) {
        DialogType.SIMPLE ->
            AlertDialog(
                text = {
                    Text(item.subtitle)
                },
                buttons = {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Ok")
                    }
                },
                onDismissRequest = onDismiss
            )
        DialogType.TITLE ->
            AlertDialog(
                title = { Text(text = item.title, style = typography.h6) },
                text = {
                    Text(item.subtitle)
                },
                buttons = {
                    Row(modifier = Modifier) {
                        TextButton(
                            onClick = onDismiss,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = "Cancel", color = Color.Gray)
                        }
                        TextButton(
                            onClick = onDismiss,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = "Ok")
                        }
                    }
                },
                onDismissRequest = onDismiss
            )
        DialogType.VERTICALBUTTON ->
            AlertDialog(
                title = { Text(text = item.title, style = typography.h6) },
                text = {
                    Text(item.subtitle)
                },
                buttons = {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(100.dp)
                    ) {
                        Text(text = "Cancel")
                    }
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(100.dp)
                    ) {
                        Text(text = "Ok")
                    }
                },
                onDismissRequest = onDismiss
            )
        DialogType.IMAGE ->
            AlertDialog(
                title = { Text(text = item.title, style = typography.h6) },
                text = {
                    Text(item.subtitle, modifier = Modifier.padding(bottom = 8.dp))
                    Image(
                        painter = painterResource(DemoDataProvider.item.imageId),
                        contentDescription = null
                    )
                },
                buttons = {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Ok")
                    }
                },
                onDismissRequest = onDismiss
            )
        DialogType.LONGDIALOG ->
            AlertDialog(
                title = { Text(text = item.title, style = typography.h6) },
                text = {
                    Text(item.subtitle, modifier = Modifier.padding(bottom = 8.dp))
                    Image(
                        painter = painterResource(DemoDataProvider.item.imageId),
                        contentDescription = null
                    )
                    Text(
                        item.subtitle + item.title + item.subtitle + item.title,
                        style = typography.subtitle2
                    )
                },
                buttons = {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Ok")
                    }
                },
                onDismissRequest = onDismiss,
            )
        DialogType.ROUNDED ->
            AlertDialog(
                title = { Text(text = item.title, style = typography.h6) },
                text = {
                    Text(item.subtitle, modifier = Modifier.padding(bottom = 8.dp))
                    Image(
                        painter = painterResource(DemoDataProvider.item.imageId),
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    )
                },
                buttons = {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Ok")
                    }
                },
                onDismissRequest = onDismiss,
                shape = RoundedCornerShape(16.dp)
            )

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeCookBookTheme {
        DialogsOptionList()
    }
}