import com.guru.composecookbook.build.dependencies.addCoreAndroidDependencies
import com.guru.composecookbook.build.dependencies.addDataDependencies
import com.guru.composecookbook.build.dependencies.addNetworkingDependencies

plugins {
    /**
     * See [common-kotlin-module-configs-script-plugin.gradle.kts] file
     */
    id("common-kotlin-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.cryptoapp.data"
}

dependencies {
    addCoreAndroidDependencies()
    addNetworkingDependencies()
    addDataDependencies()
}
