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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")

    // in order to be able to apply "com.google.devtools.ksp" in the common scripts
    implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:1.9.22-1.0.17")

    // in order to recognize the "plugins" block in the common script
    implementation("com.android.tools.build:gradle:8.2.2")

    // in order to recognize the "android" block in the common script
    implementation("com.android.tools.build:gradle-api:8.2.2")
}
