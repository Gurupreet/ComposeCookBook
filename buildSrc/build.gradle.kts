plugins {
    `kotlin-dsl`
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
    }
}

repositories {
    mavenCentral()
}