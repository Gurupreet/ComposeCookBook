---
title: "Bug/Deprecation: Migrate from ExoPlayer 2.x to AndroidX Media3"
labels: bug, dependencies, deprecation
---

## Summary

The project depends on `com.google.android.exoplayer:exoplayer:2.19.1`, which is **deprecated** and no longer receiving new features. Google has migrated ExoPlayer to **AndroidX Media3**.

## Impact

- ExoPlayer 2.x is in maintenance-only mode; no new features are added.
- Building against deprecated libraries increases future technical debt.
- Media3 is the recommended library for all new Android media development.
- The project's `demos/youtube` and any other screens using ExoPlayer will need updating.

## Proposed Changes

1. Replace `com.google.android.exoplayer:exoplayer:2.19.1` with:
   ```
   androidx.media3:media3-exoplayer:1.x.x
   androidx.media3:media3-ui:1.x.x
   ```
2. Update import statements in all usages from `com.google.android.exoplayer2.*` to `androidx.media3.*`.
3. Update `Versions.kt` to remove the `exoplayer` entry and add a `media3` entry.
4. Update `Dependencies.kt` and `GroupedDependencies.kt` accordingly.
5. Test playback in the YouTube demo screen.

## References

- [ExoPlayer migration guide to Media3](https://developer.android.com/guide/topics/media/media3/getting-started/migration-guide)
- [Media3 release notes](https://github.com/androidx/media/releases)
