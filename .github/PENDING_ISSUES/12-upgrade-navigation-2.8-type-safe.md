---
title: "Enhancement: Upgrade Navigation Compose to 2.8.x and adopt type-safe navigation"
labels: enhancement, dependencies
---

## Summary

The project uses `navCompose = "2.7.7"`. Navigation Compose **2.8.0** is stable and introduces **type-safe navigation** using Kotlin serialization, eliminating stringly-typed route strings.

## Key Improvements in Navigation 2.8

- **Type-safe routes** via `@Serializable` data classes instead of string route patterns — no more parsing `navBackStackEntry.arguments?.getString("id")`.
- `NavController.navigate(MyRoute(id = 42))` instead of `navController.navigate("detail/42")`.
- Compatible with Kotlin serialization (`kotlinx.serialization`).
- Improved `NavController` query APIs.

## Proposed Changes

1. In `Versions.kt`, update:
   ```kotlin
   const val navCompose = "2.8.x"  // e.g. "2.8.9"
   ```
2. Add `kotlinx-serialization-json` to `Dependencies.kt` and `kotlinDependencies` in `GroupedDependencies.kt`.
3. Apply the `kotlin("plugin.serialization")` Gradle plugin in `build.gradle.kts` (root).
4. Migrate existing string routes in the app to `@Serializable` data class routes.
5. Update `DependencyHandlerExtensions.kt` to include the serialization dependency.
6. Verify all navigation in demos still works correctly.

## References

- [Navigation 2.8 type-safe navigation guide](https://developer.android.com/guide/navigation/design/type-safety)
- [Navigation release notes](https://developer.android.com/jetpack/androidx/releases/navigation)
- [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html)
