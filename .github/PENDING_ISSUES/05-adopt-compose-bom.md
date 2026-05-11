---
title: "Enhancement: Adopt Compose BOM for unified Compose version management"
labels: enhancement, dependencies
---

## Summary

The project currently pins individual Compose artifact versions via a single `compose` version constant. The **Compose Bill of Materials (BOM)** is the recommended way to manage Compose library versions, ensuring all libraries are always at compatible versions.

## Motivation

- The Compose BOM guarantees all Compose libraries are version-compatible with each other, eliminating the risk of mismatched artifact versions causing runtime crashes.
- Updating Compose only requires updating one BOM version instead of multiple individual version strings.
- Google actively maintains and publishes the BOM; it's the recommended approach in official documentation.
- Currently `compose = "1.6.1"` is used for `ui`, `material`, `runtime`, etc. — the BOM maps all of these automatically.

## Proposed Changes

1. Add a `composeBom` version to `Versions.kt` (e.g., `const val composeBom = "2024.xx.xx"`).
2. In `Dependencies.kt`, add a BOM platform dependency:
   ```kotlin
   const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
   ```
3. In `DependencyHandlerExtensions.kt`, add `platform(Dependencies.composeBom)` as a BOM import before adding individual Compose artifacts.
4. Remove the explicit version strings from Compose artifact declarations that are covered by the BOM (e.g., `composeUi`, `composeMaterial`, `composeRuntime`).
5. Keep `material3` version explicit if needed (or let BOM manage it too).
6. Remove the standalone `compose` and `material3` version constants from `Versions.kt` once fully managed by BOM.

## References

- [Compose BOM documentation](https://developer.android.com/jetpack/compose/setup#bom-version-mapping)
- [Compose BOM to library version mapping](https://developer.android.com/jetpack/compose/bom/bom-mapping)
