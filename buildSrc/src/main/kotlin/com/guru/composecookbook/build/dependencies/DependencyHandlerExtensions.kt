package com.guru.composecookbook.build.dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addComposeOfficialDependencies() {
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
    add("kapt", Dependencies.roomCompiler)
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
    androidInstrumentationTestsDependencies.forEach {
        add("androidTestImplementation", it)
    }
}

fun DependencyHandler.addThirdPartyUiDependencies() {
    thirdPartyUiDependencies.forEach {
        add("implementation", it)
    }
}
