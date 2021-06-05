import com.guru.composecookbook.build.dependencies.*

plugins {
    /**
     * See [common-compose-module-configs-script-plugin.gradle.kts] file
     */
    id("common-compose-module-configs-script-plugin")
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