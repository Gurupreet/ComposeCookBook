package com.guru.composecookbook.build.configurations

import com.guru.composecookbook.build.dependencies.Versions

object ProjectConfigs {
    const val compileSdkVersion = 34  // Latest supported Android SDK version
    const val minSdkVersion = 25  // Original minSdkVersion
    const val targetSdkVersion = 34  // Matching compileSdkVersion
    const val applicationId = "com.guru.composecookbook"
    const val kotlinCompilerExtensionVersion = Versions.composeCompiler
}