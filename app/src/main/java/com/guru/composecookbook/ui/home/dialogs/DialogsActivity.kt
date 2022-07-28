package com.guru.composecookbook.ui.home.dialogs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.home.dynamic.DynamicUIActivity


class DialogsActivity : AppCompatActivity() {

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DynamicUIActivity.DARK_THEME, false) ?: false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookMaterial3Theme(isDarkTheme) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = "Dialogs") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.cd_back)
                        )
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

        Button(
            onClick = { dialogState = DialogState(true, DialogType.DATEPICKER) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Date Picker Dialog")
        }

        Button(
            onClick = { dialogState = DialogState(true, DialogType.TIMEPICKER) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Time Picker Dialog")
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
                confirmButton = {
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
                confirmButton = {
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
                confirmButton = {
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
                confirmButton = {
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
                    Column {
                        Text(item.subtitle, modifier = Modifier.padding(bottom = 8.dp))
                        Image(
                            painter = painterResource(DemoDataProvider.item.imageId),
                            contentDescription = null
                        )
                        Text(
                            item.subtitle + item.title + item.subtitle + item.title,
                            style = typography.subtitle2
                        )
                    }
                },
                confirmButton = {
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
                    Column {
                        Text(item.subtitle, modifier = Modifier.padding(bottom = 8.dp))
                        Image(
                            painter = painterResource(DemoDataProvider.item.imageId),
                            contentDescription = null,
                            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                        )
                    }
                },
                confirmButton = {
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
        DialogType.DATEPICKER -> {
            val context = LocalContext.current
            (context as? FragmentActivity)?.supportFragmentManager?.let { manager ->

                val builder = MaterialDatePicker.Builder.datePicker()
                    .build()

                builder.addOnPositiveButtonClickListener { selectedDate ->

                }
                builder.addOnDismissListener {
                    onDismiss()
                }
                builder.show(manager, "DatePicker")
            }
        }

        DialogType.TIMEPICKER -> {
            val context = LocalContext.current
            (context as? FragmentActivity)?.supportFragmentManager?.let { manager ->
                val builder = MaterialTimePicker.Builder()
                    .build()
                builder.addOnPositiveButtonClickListener {

                }
                builder.addOnDismissListener {
                    onDismiss.invoke()
                }
                builder.show(manager, "TimePicker")
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeCookBookMaterial3Theme {
        DialogsOptionList()
    }
}