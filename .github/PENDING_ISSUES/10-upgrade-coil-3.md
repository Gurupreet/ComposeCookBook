---
title: "Enhancement: Upgrade Coil to 3.x"
labels: enhancement, dependencies
---

## Summary

The project uses **Coil 2.5.0** (`io.coil-kt:coil-compose`). Coil 3.0 is now stable and is a major version bump with breaking changes that also bring significant improvements.

## Key Changes in Coil 3

- **Multiplatform support** — Coil 3 supports Kotlin Multiplatform (KMP), enabling future Desktop/Web targets.
- **Improved performance** — Faster image loading and better memory management.
- **New artifact group**: `io.coil-kt.coil3:coil-compose` (note the `coil3` group ID).
- **OkHttp is now optional**: Coil 3 uses Ktor or OkHttp interchangeably; `coil-network-okhttp` sub-artifact needed to keep OkHttp.
- **Breaking API changes**: `AsyncImage` API is largely the same but `ImageLoader` configuration has changed.

## Proposed Changes

1. In `Versions.kt`, update:
   ```kotlin
   const val coilCompose = "3.x.x"  // e.g. "3.1.0"
   ```
2. In `Dependencies.kt`, update the artifact group:
   ```kotlin
   const val coilCompose = "io.coil-kt.coil3:coil-compose:${Versions.coilCompose}"
   const val coilNetworkOkHttp = "io.coil-kt.coil3:coil-network-okhttp:${Versions.coilCompose}"
   ```
3. Add `coilNetworkOkHttp` to `GroupedDependencies.kt` alongside `coilCompose`.
4. Update any call sites that use deprecated Coil 2 APIs.
5. Ensure all demo screens that load images (Instagram, Movies, Crypto, etc.) still function correctly.

## References

- [Coil 3 Migration Guide](https://coil-kt.github.io/coil/upgrading_to_coil3/)
- [Coil 3 GitHub Releases](https://github.com/coil-kt/coil/releases)
