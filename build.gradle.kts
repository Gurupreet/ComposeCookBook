
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-alpha01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.kotlin}")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
        jcenter()
    }
}
