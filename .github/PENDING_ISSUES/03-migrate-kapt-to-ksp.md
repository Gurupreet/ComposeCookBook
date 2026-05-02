---
title: "Enhancement: Migrate Room annotation processing from KAPT to KSP"
labels: enhancement, performance, dependencies
---

## Summary

The project uses `kotlin-kapt` to process Room's annotation processor (`androidx.room:room-compiler`). **KSP (Kotlin Symbol Processing)** is the modern replacement for KAPT and is now the recommended processor for Room.

## Motivation

- **KSP is ~2x faster** than KAPT because it runs natively in Kotlin, avoiding the Java stub generation step.
- `kotlin-kapt` is in maintenance mode; JetBrains recommends moving to KSP.
- Room has first-class KSP support since Room 2.5.0 (current version is 2.6.1).
- The `app/build.gradle.kts` already uses `kotlin-kapt` and has `kapt { correctErrorTypes = true }` which can be removed.

## Proposed Changes

1. In `app/build.gradle.kts` (and any module using Room), replace:
   ```kotlin
   id("kotlin-kapt")
   ```
   with:
   ```kotlin
   id("com.google.devtools.ksp")
   ```
2. In `build.gradle.kts` (root), add the KSP plugin to the buildscript classpath.
3. In `DependencyHandlerExtensions.kt`, change `add("kapt", Dependencies.roomCompiler)` to `add("ksp", Dependencies.roomCompiler)`.
4. Update `Dependencies.kt`: `room-compiler` artifact stays the same; only the configuration changes.
5. Remove the `kapt { correctErrorTypes = true }` block from `app/build.gradle.kts`.
6. Add `ksp` version constant to `Versions.kt` (match the Kotlin version).

## References

- [KSP Quick Start](https://kotlinlang.org/docs/ksp-quickstart.html)
- [Room KSP Support](https://developer.android.com/training/data-storage/room#ksp)
