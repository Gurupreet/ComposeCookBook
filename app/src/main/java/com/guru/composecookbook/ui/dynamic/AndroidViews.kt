package com.guru.composecookbook.ui.dynamic

import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.ui.home.HomeScreenItems
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AndroidViews() {
    ScrollableColumn {
        TitleText(title = "Android Views with Compose")
        val context = ContextAmbient.current
        AndroidTextView(context)
        AndroidButton(context)
    }
}

@Composable
fun AndroidTextView(context: Context) {
    SubtitleText(subtitle = "Below is Android Textview")
    //simple android textview creation
    // if you are using xml it can be textview = remember { findViewById(R.id.androidTextView }
    val androidTextView = remember {
        TextView(context).apply {
            setText(R.string.about_me)
            setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }
    AndroidView({ androidTextView }, modifier = Modifier.padding(8.dp)) {
        // Textview inflated in compose
        // Re composition will call this section again
        //   it.setText(R.string.about_me)
    }
}

@Composable
fun AndroidButton(context: Context) {
    var count by remember { mutableStateOf(0) }
    SubtitleText(subtitle = "Below is Android Button")
    //simple android button creation
    val androidButton = remember {
        Button(context).apply {
            // init state of button
            text = "Click me: $count"
            setOnClickListener { count++ }
        }
    }
    AndroidView({ androidButton }, modifier = Modifier.padding(8.dp)) {
        // Button inflated in compose
        // Re composition will call this section again
        it.text = "Click me: $count"
    }
}

@Composable
fun AndroidAdView(context: Context) {
    SubtitleText(subtitle = "Android AdView")
}

@Preview
@Composable
fun ShowAndroidViews() {
    AndroidViews()
}