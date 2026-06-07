import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidUiDependencies
import com.guru.composecookbook.build.dependencies.addGoogleAndroidDependencies
import com.guru.composecookbook.build.dependencies.addKotlinDependencies
import com.guru.composecookbook.build.dependencies.addThirdPartyUiDependencies

plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
  id("org.jetbrains.kotlin.plugin.serialization")
}

android { namespace = "com.guru.composecookbook.tiktok" }

dependencies {
  implementation(project(":components:carousel"))
  implementation(project(":components:verticalgrid"))
  implementation(project(":data"))
  implementation(project(":theme"))

  addKotlinDependencies()
  addComposeOfficialDependencies()
  addCoreAndroidUiDependencies()
  addThirdPartyUiDependencies()
  addGoogleAndroidDependencies()
}
