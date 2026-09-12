package com.guru.composecookbook.build.dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

/** Imports the Compose BOM so the unversioned Compose artifacts resolve consistently. */
private fun DependencyHandler.addComposeBom(configurationName: String) {
    add(configurationName, platform(Dependencies.composeBom))
}

fun DependencyHandler.addComposeOfficialDependencies() {
    addComposeBom("implementation")
    composeOfficialDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addComposeThirdPartyDependencies() {
    composeThirdPartyDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addComposeDebugDependencies() {
    addComposeBom("debugImplementation")
    composeDebugDependencies.forEach {
        add("debugImplementation", it)
    }
}

fun DependencyHandler.addKotlinDependencies() {
    kotlinDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addKotlinTestDependencies() {
    kotlinTestDependencies.forEach {
        add("testImplementation", it)
    }
}

fun DependencyHandler.addDataDependencies() {
    addComposeBom("implementation")
    add("ksp", Dependencies.roomCompiler)
    dataDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addCoreAndroidDependencies() {
    coreAndroidDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addCoreAndroidUiDependencies() {
    coreAndroidUiDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addGoogleAndroidDependencies() {
    googleAndroidLibraries.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addNetworkingDependencies() {
    networkingDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addJunit5TestDependencies() {
    junit5TestDependencies.forEach {
        add("testImplementation", it)
    }
}

fun DependencyHandler.addThirdPartyUnitTestsDependencies() {
    thirdPartyUnitTestsDependencies.forEach {
        add("testImplementation", it)
    }
}

fun DependencyHandler.addAndroidInstrumentationTestsDependencies() {
    addComposeBom("androidTestImplementation")
    androidInstrumentationTestsDependencies.forEach {
        add("androidTestImplementation", it)
    }
}

fun DependencyHandler.addThirdPartyUiDependencies() {
    thirdPartyUiDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addBiometricDependency() {
    add("implementation", Dependencies.biometric)
}