---
title: "Enhancement: Upgrade Kotlin to 2.1.x"
labels: enhancement, dependencies
---

## Summary

The project currently uses **Kotlin 1.9.22**. Kotlin 2.0 is now stable and 2.1.x is the latest release, bringing significant improvements.

## Motivation

- **Kotlin 2.0 K2 Compiler** is ~2x faster than the K1 compiler, reducing build times.
- **New Compose Compiler Gradle Plugin** — in Kotlin 2.0+, the Compose compiler is distributed as a separate Kotlin compiler plugin (`org.jetbrains.kotlin.plugin.compose`) instead of being tied to the Kotlin version. This simplifies version management.
- Bug fixes, new language features (e.g. improved `data object`, power assert plugin), and improved type inference.
- Kotlin 1.9.x reaches end-of-life maintenance.

## Proposed Changes

1. Bump `kotlin-gradle-plugin` in `build.gradle.kts` from `1.9.22` → `2.1.x` (e.g. `2.1.20`).
2. In `buildSrc/src/main/kotlin/.../Versions.kt`, update `kotlin = "1.9.22"` → `"2.1.20"`.
3. Switch the Compose compiler from `kotlinCompilerExtensionVersion` to the new `org.jetbrains.kotlin.plugin.compose` Gradle plugin (see [official migration guide](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compiler.html)).
4. Remove the now-redundant `composeCompiler` version constant from `Versions.kt` and `ProjectConfigs.kt`.
5. Verify all modules compile and tests pass.

## References

- [Kotlin 2.0 release blog](https://blog.jetbrains.com/kotlin/2024/05/kotlin-2-0-0-release/)
- [Compose Compiler Migration Guide](https://developer.android.com/develop/ui/compose/compiler)
