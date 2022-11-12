package com.guru.composecookbook.build.dependencies

object Dependencies {

    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val fontAwesomeCompose =
        "com.github.Gurupreet:FontAwesomeCompose:${Versions.fontAwesomeCompose}"
    const val kotlinStandardLibraryJdk8 =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidPaletteKtx = "androidx.palette:palette-ktx:${Versions.paletteKtx}"
    const val googleMaterial = "com.google.android.material:material:${Versions.material}"
    const val androidMultiDex = "androidx.multidex:multidex:${Versions.multidex}"
    const val playServicesAds =
        "com.google.android.gms:play-services-ads:${Versions.playServicesAds}"
    const val googleMaps = "com.google.android.libraries.maps:maps:${Versions.googleMaps}"
    const val playServicesMaps =
        "com.google.android.gms:play-services-maps:${Versions.playServicesMaps}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.material3}"
    const val composeMaterialIconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeRuntimeLivedata =
        "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    const val composePaging = "androidx.paging:paging-compose:${Versions.pagingCompose}"
    const val composeViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.androidLifecycleGrouped}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeLottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"

    const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    /**
     * Custom fling behaviour
     * refer to https://github.com/iamjosephmj/flinger for more insights on the library
     */
    const val flinger = "com.github.iamjosephmj:flinger:${Versions.flinger}"

    const val androidPagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val androidExoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okHttpGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val viewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycleGrouped}"
    const val liveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifecycleGrouped}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidLifecycleGrouped}"
    const val lifecycleSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidLifecycleGrouped}"
    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiterApi}"
    const val junitJupiterEngine =
        "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiterEngine}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val kotlinJunit5 = "org.jetbrains.kotlin:kotlin-test-junit5:${Versions.kotlin}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    const val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJunit}"

    const val biometric = "androidx.biometric:biometric:${Versions.biometric}"

}
