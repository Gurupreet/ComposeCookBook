import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidUiDependencies

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.paint"
}

dependencies {
    implementation(project(":theme"))
    implementation(project(":components:colorpicker"))

    addComposeOfficialDependencies()
    addCoreAndroidUiDependencies()
}
