---
title: "Enhancement: Migrate to Gradle Version Catalog (libs.versions.toml)"
labels: enhancement, build
---

## Summary

The project manages dependency versions via a custom `buildSrc` Kotlin module. While functional, the modern Gradle best practice is to use a **Version Catalog** (`gradle/libs.versions.toml`). This is the default in all new Android Studio projects as of Flamingo (2022.2.1).

## Motivation

- Version Catalog provides IDE auto-complete for dependency declarations.
- Single source of truth — no separate `Versions.kt`, `Dependencies.kt`, `GroupedDependencies.kt`, and `DependencyHandlerExtensions.kt` files.
- Enables Gradle's built-in `./gradlew dependencyUpdates` and Renovate/Dependabot to suggest version bumps automatically.
- Works seamlessly with `buildSrc` convention plugins if those are kept.
- Reduces boilerplate: `libs.compose.ui` instead of `Dependencies.composeUi`.

## Proposed Changes

1. Create `gradle/libs.versions.toml` with all versions, libraries, and plugins currently defined in `buildSrc/src/main/kotlin/.../Versions.kt` and `Dependencies.kt`.
2. Migrate `app/build.gradle.kts` and all module `build.gradle.kts` files to reference `libs.<alias>` instead of `Dependencies.*`.
3. Keep the convention plugins in `buildSrc` (`common-kotlin-module-configs-script-plugin.gradle.kts`, `common-compose-module-configs-script-plugin.gradle.kts`) but have them call `libs.*` instead of the Kotlin constants.
4. Delete `buildSrc/src/main/kotlin/.../Versions.kt`, `Dependencies.kt`, `GroupedDependencies.kt`, and `DependencyHandlerExtensions.kt`.
5. Update `settings.gradle.kts` to enable the version catalog feature (already on by default in AGP 8+).

## References

- [Gradle Version Catalogs docs](https://docs.gradle.org/current/userguide/version_catalogs.html)
- [Android docs: Migrate to version catalogs](https://developer.android.com/build/migrate-to-catalogs)
