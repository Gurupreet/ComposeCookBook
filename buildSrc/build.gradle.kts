plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
    }
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    // in order to be able to use "kotlin-android" in the common script
    implementation(libs.kotlin.gradle.plugin)

    // in order to be able to apply "org.jetbrains.kotlin.plugin.compose" in the common scripts
    implementation(libs.compose.compiler.gradle.plugin)

    // in order to be able to apply "org.jetbrains.kotlin.plugin.serialization" in module scripts
    implementation(libs.kotlin.serialization.plugin)

    // in order to be able to apply "com.google.devtools.ksp" in the common scripts
    // (the KSP version in the catalog must match the Kotlin version)
    implementation(libs.ksp.gradle.plugin)

    // in order to recognize the "plugins" block in the common script
    implementation(libs.android.gradle.plugin)

    // in order to recognize the "android" block in the common script
    implementation(libs.android.gradle.api)
}
