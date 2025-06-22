plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-kotlin-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.data"
}

dependencies {
    implementation(com.guru.composecookbook.build.dependencies.Dependencies.composeRuntime)
}
