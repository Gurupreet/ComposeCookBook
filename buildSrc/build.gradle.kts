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
    // for the Kotlin Gradle plugin DSL (kotlin { compilerOptions }, JvmTarget) in the common scripts
    implementation(libs.kotlin.gradle.plugin)

    // in order to be able to apply "org.jetbrains.kotlin.plugin.compose" in the common scripts
    implementation(libs.compose.compiler.gradle.plugin)

    // in order to be able to apply "org.jetbrains.kotlin.plugin.serialization" in module scripts
    implementation(libs.kotlin.serialization.plugin)

    // in order to be able to apply "com.google.devtools.ksp" in the common scripts
    // (KSP 2.3.x is versioned independently of Kotlin since Kotlin 2.2)
    implementation(libs.ksp.gradle.plugin)

    // in order to recognize the "plugins" block in the common script
    implementation(libs.android.gradle.plugin)

    // in order to recognize the "android" block in the common script
    implementation(libs.android.gradle.api)
}
