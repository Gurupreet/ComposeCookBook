---
title: "Enhancement: Bump compileSdk and targetSdk to API 35 (Android 15)"
labels: enhancement, dependencies
---

## Summary

The project currently targets **Android 14 (API 34)**. Android 15 (API 35) is now stable and Google Play requires apps to target it by August 2025.

## Motivation

- Apps targeting API 35 unlock Android 15 features (edge-to-edge enforcement, predictive back, photo picker improvements, health connect).
- Google Play's target API level policy requires apps to target API 35 by **August 31, 2025** for updates to existing apps.
- Jetpack Compose components have improved behavior when `targetSdk = 35` (e.g., better edge-to-edge inset handling).

## Proposed Changes

1. In `buildSrc/src/main/kotlin/.../ProjectConfigs.kt`:
   ```kotlin
   const val compileSdkVersion = 35
   const val targetSdkVersion = 35
   ```
2. Ensure the Android Gradle Plugin version in `build.gradle.kts` supports SDK 35 (AGP 8.2.2 supports it; AGP 8.3+ recommended).
3. Test for any behavior changes introduced by targeting API 35:
   - **Edge-to-edge** is enforced by default on Android 15; ensure all screens handle window insets correctly using `WindowInsets` composables.
   - Verify the app handles the predictive back gesture where applicable.
4. Update the README badge which references an old Compose version.

## References

- [Android 15 Behavior Changes](https://developer.android.com/about/versions/15/behavior-changes-15)
- [Google Play target API requirements](https://developer.android.com/google/play/requirements/target-sdk)
