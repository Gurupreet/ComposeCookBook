package com.guru.composecookbook.ui.androidviews

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.LifecycleOwnerAmbient
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.guru.composecookbook.R
import com.guru.composecookbook.ui.utils.SubtitleText
import com.guru.composecookbook.ui.utils.TitleText

@Composable
fun AndroidViews() {
    ScrollableColumn {
        TitleText(title = "Android Views Inside Compose(ScrollableColumn)")
        val context = ContextAmbient.current
        AndroidTextView(context)
        AndroidButton(context)
        AndroidLottieView(context)
        AndroidLottieView2(context)
        AndroidAdView(context)
        AndroidMapView()
        Spacer(modifier = Modifier.height(100.dp))
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
fun AndroidLottieView(context: Context) {
    SubtitleText(subtitle = "Android LottieView hosted in compose")
    val lottieView = remember {
        LottieAnimationView(context).apply {
            loop(true)
            setAnimation("loader.json")
        }
    }

    AndroidView({ lottieView }, modifier = Modifier.fillMaxWidth().height(200.dp)) {
        it.playAnimation()
    }
}

@Composable
fun AndroidLottieView2(context: Context) {
    val lottieView = remember {
        LottieAnimationView(context).apply {
            loop(true)
            setAnimation("food.json")
        }
    }

    AndroidView({ lottieView }, modifier = Modifier.fillMaxWidth().height(200.dp)) {
        it.playAnimation()
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
fun AndroidMapView() {
    //Please Add your map api key to manifest to see the map.
    SubtitleText(subtitle = "Android MapView")
    val map = rememberMapViewWithLifecycle()
    // Taken from compose samples checkout below for official demo.
    // https://github.com/android/compose-samples/tree/master/Crane/app/src/main/java/androidx/compose/samples/crane/details
    MapViewContainer(map = map, latitude = "1.3521", longitude = "103.8198")
}

@Composable
private fun MapViewContainer(
    map: MapView,
    latitude: String,
    longitude: String
) {
    AndroidView(
        { map },
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
    ) { mapView ->
        // I think this is slowing the performance of map view loading needs checking !!
        mapView.getMapAsync {
            val position = LatLng(latitude.toDouble(), longitude.toDouble())
            it.addMarker(
                MarkerOptions().position(position)
            )
            it.moveCamera(CameraUpdateFactory.newLatLng(position))
        }
    }
}


@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = ContextAmbient.current
    val mapView = remember {
        MapView(context)
    }

    // Makes MapView follow the lifecycle of this composable
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LifecycleOwnerAmbient.current.lifecycle
    onCommit(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

@Composable
private fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }

@Preview
@Composable
fun ShowAndroidViews() {
    AndroidViews()
}