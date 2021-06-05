import com.guru.composecookbook.build.dependencies.*

plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-kotlin-module-configs-script-plugin")
}

dependencies {
    addCoreAndroidDependencies()
    addNetworkingDependencies()
    addDataDependencies()
}
