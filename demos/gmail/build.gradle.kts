import com.guru.composecookbook.build.dependencies.addComposeOfficialDependencies
import com.guru.composecookbook.build.dependencies.addCoreAndroidUiDependencies
import com.guru.composecookbook.build.dependencies.addKotlinDependencies

plugins {
  /** See [common-compose-module-configs-script-plugin.gradle.kts] file */
  id("common-compose-module-configs-script-plugin")
  id("org.jetbrains.kotlin.plugin.serialization")
}

android { namespace = "com.guru.composecookbook.gmail" }

dependencies {
  implementation(project(":components:fab"))
  implementation(project(":data"))
  implementation(project(":theme"))

  addKotlinDependencies()
  addComposeOfficialDependencies()
  addCoreAndroidUiDependencies()
}
