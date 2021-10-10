buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-alpha12")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
        jcenter()
    }
}
