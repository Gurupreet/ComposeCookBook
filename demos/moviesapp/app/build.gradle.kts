import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addComposeThirdPartyDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidUiDependencies
import com.guru.composecookbook.build.dependencies.addThirdPartyUiDependencies

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
}

android {
    namespace = "com.guru.composecookbook.moviesapp"
}

dependencies {
    implementation(project(":components:tags"))
    implementation(project(":components:carousel"))
    implementation(project(":demos:moviesapp:data"))
    implementation(project(":theme"))
    implementation(project(":data"))

    addComposeOfficialDependencies()
    addComposeThirdPartyDependencies()

    addCoreAndroidUiDependencies()
    addThirdPartyUiDependencies()

    addCoreAndroidDependencies()
}