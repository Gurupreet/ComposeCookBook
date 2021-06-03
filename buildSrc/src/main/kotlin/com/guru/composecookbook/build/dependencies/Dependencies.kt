package com.guru.composecookbook.build.dependencies

object Dependencies {

    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    val fontAwesomeCompose = "com.github.Gurupreet:FontAwesomeCompose:${Versions.fontAwesomeCompose}"
    val kotlinStandardLibraryJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val androidPaletteKtx = "androidx.palette:palette-ktx:${Versions.paletteKtx}"
    val googleMaterial = "com.google.android.material:material:${Versions.material}"
    val androidMultiDex = "androidx.multidex:multidex:${Versions.multidex}"
    val playServicesAds = "com.google.android.gms:play-services-ads:${Versions.playServicesAds}"
    val googleMaps = "com.google.android.libraries.maps:maps:${Versions.googleMaps}"
    val playServicesMaps = "com.google.android.gms:play-services-maps:${Versions.playServicesMaps}"

    val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    val composeRuntimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    val composePaging = "androidx.paging:paging-compose:${Versions.pagingCompose}"
    val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}"
    val composeActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    val composeLottie = "com.airbnb.android:lottie-compose:${Versions.lottieCompose}"
    val composeNavigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"

    val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    /**
     * Custom fling behaviour
     * refer to https://github.com/iamjosephmj/flinger for more insights on the library
     */
    val flinger = "com.github.iamjosephmj:flinger:${Versions.flinger}"

    val androidPagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    val androidExoPlayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer}"
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    val coilAccompanist = "com.google.accompanist:accompanist-coil:${Versions.accompanistCoil}"
    val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val okHttpGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterGson}"
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.androidLifecycleGrouped}"
    val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifecycleGrouped}"
    val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycleGrouped}"
    val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiterApi}"
    val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiterEngine}"
    val truth = "com.google.truth:truth:${Versions.truth}"
    val kotlinJunit5 = "org.jetbrains.kotlin:kotlin-test-junit5:${Versions.kotlin}"
    val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJunit}"

}
