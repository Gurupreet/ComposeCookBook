// Plugin versions come from gradle/libs.versions.toml (the version catalog).
// AGP, Kotlin, KSP, serialization and the Compose compiler plugin reach the
// build classpath through buildSrc's dependencies (also catalog-driven), so
// only ktfmt needs to be declared here.
plugins {
    alias(libs.plugins.ktfmt) apply false
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
    }

    // Apply ktfmt to all projects
    apply(plugin = "com.ncorti.ktfmt.gradle")

    configure<com.ncorti.ktfmt.gradle.KtfmtExtension> {
        googleStyle()
        maxWidth.set(100)
    }
}
