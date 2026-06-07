plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
    }
}

buildscript {

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.20")
    }
}


repositories {
    mavenLocal()
    mavenCentral()
    google()
    google()
}

dependencies {
    // in order to be able to use "kotlin-android" in the common script
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.20")

    // in order to be able to apply "org.jetbrains.kotlin.plugin.compose" in the common scripts
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.1.20")

    // in order to be able to apply "com.google.devtools.ksp" in the common scripts
    // (KSP version must match the Kotlin version above)
    implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:2.1.20-1.0.31")

    // in order to recognize the "plugins" block in the common script
    implementation("com.android.tools.build:gradle:8.2.2")

    // in order to recognize the "android" block in the common script
    implementation("com.android.tools.build:gradle-api:8.2.2")
}
