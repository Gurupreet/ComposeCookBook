---
title: "Enhancement: Upgrade AndroidX Lifecycle to 2.8.x"
labels: enhancement, dependencies
---

## Summary

The project uses `androidLifecycleGrouped = "2.7.0"` for all lifecycle-related artifacts (`lifecycle-viewmodel-compose`, `lifecycle-runtime-ktx`, `lifecycle-livedata-ktx`, etc.). Lifecycle **2.8.x** is now stable and includes important improvements.

## Key Improvements in Lifecycle 2.8

- **`LifecycleEventEffect` and `LifecycleStartEffect`/`LifecycleResumeEffect`** composables — cleaner replacements for `DisposableEffect` with `LocalLifecycleOwner`.
- **`collectAsStateWithLifecycle`** improvements for safer Flow collection tied to UI lifecycle.
- **`viewModel()` KMP support** — multiplatform ViewModel for future KMP migration.
- Bug fixes and improved SavedState integration.

## Proposed Changes

1. In `Versions.kt`, update:
   ```kotlin
   const val androidLifecycleGrouped = "2.8.x"  // e.g. "2.8.7"
   ```
2. All lifecycle artifacts using this version constant update automatically:
   - `lifecycle-viewmodel-compose`
   - `lifecycle-viewmodel-ktx`
   - `lifecycle-livedata-ktx`
   - `lifecycle-runtime-ktx`
   - `lifecycle-viewmodel-savedstate`
3. Review usages of `DisposableEffect(lifecycleOwner)` in the codebase and consider migrating to the new `LifecycleEventEffect` API where applicable.
4. Run all tests to confirm no regressions.

## References

- [Lifecycle 2.8.0 Release Notes](https://developer.android.com/jetpack/androidx/releases/lifecycle#2.8.0)
- [LifecycleEventEffect API](https://developer.android.com/reference/kotlin/androidx/lifecycle/compose/package-summary#LifecycleEventEffect)
