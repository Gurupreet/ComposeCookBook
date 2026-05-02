---
title: "Feature: Add Adaptive Layout / Large Screen support"
labels: feature request, enhancement
---

## Summary

The app currently has no adaptive layout support for large screens (tablets, foldables, desktops). Jetpack Compose provides `WindowSizeClass` and `adaptive` layout libraries specifically for this. Adding a demo would significantly improve the showcase value of this cookbook.

## Description

Android now requires apps targeting large screens to follow [large screen quality guidelines](https://developer.android.com/docs/quality-guidelines/large-screen-app-quality). The Compose toolkit provides:

- `WindowSizeClass` (from `androidx.compose.material3:material3-window-size-class`) — already in `Dependencies.kt`.
- `androidx.compose.material3.adaptive` — new adaptive navigation and scaffold APIs.
- `NavigationSuiteScaffold` — automatically switches between `NavigationBar`, `NavigationRail`, and `NavigationDrawer` based on window size.

## Proposed Demo

A demo screen that shows:
1. **Adaptive navigation** using `NavigationSuiteScaffold` — bottom bar on compact, rail on medium, drawer on expanded.
2. **Two-pane layout** using `ListDetailPaneScaffold` from `androidx.compose.material3.adaptive.layout`.
3. Sample: a list of items on the left pane, detail view on the right pane when screen is large enough.

## Proposed Changes

1. Add `androidx.compose.material3:material3-adaptive-navigation-suite` to `Dependencies.kt`.
2. Create a new demo module or screen `demos/adaptive/`.
3. Update `app/build.gradle.kts` and navigation to include the adaptive demo.
4. Update README to document the new demo.

## Acceptance Criteria

- [ ] `NavigationSuiteScaffold` switches navigation component based on `WindowSizeClass`.
- [ ] `ListDetailPaneScaffold` shows a two-pane layout on tablets.
- [ ] Works on phone (single pane), tablet (two pane), and foldable (hinge-aware).
- [ ] Supports dark mode.

## References

- [Adaptive layouts in Compose](https://developer.android.com/develop/ui/compose/layouts/adaptive)
- [NavigationSuiteScaffold](https://developer.android.com/reference/kotlin/androidx/compose/material3/adaptive/navigationsuite/package-summary)
- [ListDetailPaneScaffold](https://developer.android.com/reference/kotlin/androidx/compose/material3/adaptive/layout/package-summary)
