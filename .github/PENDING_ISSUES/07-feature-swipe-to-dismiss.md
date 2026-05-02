---
title: "Feature: Add Swipe-to-Dismiss / Swipe Actions list demo"
labels: feature request, enhancement
---

## Summary

The README lists "Swipe lists" under "Coming Soon." Compose Material3 provides `SwipeToDismissBox`, the recommended API for implementing swipe-to-dismiss and swipe actions on list items.

## Description

This feature would add a demo showcasing:

1. **Basic SwipeToDismissBox** — swipe an item left to delete it from a `LazyColumn`.
2. **Swipe with reveal actions** — swipe right to reveal an action button (e.g. "Archive").
3. **Undo delete** — a `Snackbar` with an "Undo" action after dismissal, demonstrating the interaction pattern recommended by Material Design 3 guidelines.

## Proposed Location

- New component in `components/swipedismiss/` or as a section within an existing "Lists" demo screen.
- Exposed from the Home screen under "Advance Lists & Animations."

## Acceptance Criteria

- [ ] `SwipeToDismissBox` demo with directional thresholds configured.
- [ ] Background shown during swipe (e.g. red "Delete" background with icon).
- [ ] Item is removed from the list on full swipe.
- [ ] Undo `Snackbar` appears after deletion.
- [ ] Supports dark mode.
- [ ] Added to navigation and the Home screen list.
- [ ] README updated.

## References

- [Material3 SwipeToDismissBox docs](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#SwipeToDismissBox)
- [Material Design 3 Swipe guidelines](https://m3.material.io/components/lists/guidelines)
