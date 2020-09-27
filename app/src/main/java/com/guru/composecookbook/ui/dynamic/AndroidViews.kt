package com.guru.composecookbook.ui.dynamic

import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.ui.tooling.preview.Preview
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.guru.composecookbook.R
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

@Composable
fun AndroidViews() {
    ScrollableColumn {
        TitleText(title = "Android Views with Compose")
        val context = ContextAmbient.current
        AndroidTextView(context)
        AndroidButton(context)
        AndroidAdView(context)
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
    // AndroidView is composable  which hosts android views.
    AndroidView({ androidButton }, modifier = Modifier.padding(8.dp)) {
        // Button inflated in compose
        // Re composition will call this section again
        it.text = "Click me: $count"
    }
}

@Composable
fun AndroidAdView(context: Context) {
    SubtitleText(subtitle = "Android AdView")

    val adView = remember {
        val adRequest: AdRequest = AdRequest.Builder().build()
        AdView(context).apply {
            // init test ads
            adSize = AdSize.SMART_BANNER
            adUnitId = resources.getString(R.string.test_adbanner_id)
            loadAd(adRequest)
        }
    }

    AndroidView({ adView }, modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.Gray))
}

@Composable
fun AndroidMapView(context: Context) {
    SubtitleText(subtitle = "Android MapView")

//    val adView = remember {
//        val adRequest: AdRequest = AdRequest.Builder().build()
//        Map(context).apply {
//            // init test ads
//            adSize = AdSize.LARGE_BANNER
//            adUnitId = resources.getString(R.string.test_adbanner_id)
//            loadAd(adRequest)
//        }
//    }
//
//    AndroidView({ adView }, modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.Gray))
}

@Preview
@Composable
fun ShowAndroidViews() {
    AndroidViews()
}