---
title: "Feature: Add Pull-to-Refresh demo"
labels: feature request, enhancement
---

## Summary

The README lists "Pull Refresh" under "Coming Soon." Jetpack Compose Material3 introduced a first-party `PullToRefreshBox` composable. This feature should be demonstrated in the app.

## Description

Compose Material3 ships `androidx.compose.material3.pulltorefresh.PullToRefreshBox` (stable in Material3 1.3.0+). Adding a demo would cover:

- Basic `PullToRefreshBox` usage showing a loading indicator.
- Integration with a `ViewModel` and a `LazyColumn` that reloads data on pull.
- Optionally, a custom indicator to demonstrate customization.

## Proposed Location

Add the demo to one of:
- **Home screen list** — as a new list item under "Advance Lists & Animations."
- **A new screen** in `components/` — `components/pullrefresh/`.

## Acceptance Criteria

- [ ] A composable screen demonstrating `PullToRefreshBox` (or `PullToRefresh` with `rememberPullToRefreshState()`).
- [ ] Simulates a network delay (e.g. `delay(1500)`) to show the indicator in action.
- [ ] Supports both light and dark themes.
- [ ] Registered in the app's navigation/home screen list.
- [ ] README "Coming Soon" section updated to move this item to "Done."

## References

- [Material3 Pull-to-Refresh docs](https://developer.android.com/reference/kotlin/androidx/compose/material3/pulltorefresh/package-summary)
- [Compose Samples — PullToRefresh](https://github.com/android/compose-samples)
