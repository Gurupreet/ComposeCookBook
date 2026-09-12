// Expose the root version catalog to buildSrc so plugin/dependency
// versions live in a single place: gradle/libs.versions.toml
dependencyResolutionManagement {
    versionCatalogs { create("libs") { from(files("../gradle/libs.versions.toml")) } }
}
