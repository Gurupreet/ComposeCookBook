import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidDependencies
import com.guru.composecookbook.build.dependencies.addThirdPartyUiDependencies

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.lottie"
}

dependencies {
    addComposeOfficialDependencies()
    addCoreAndroidDependencies()
    addThirdPartyUiDependencies()
}
