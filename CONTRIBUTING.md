# Contributing to ComposeCookBook

Thanks for helping out! All contributions are welcome. Please read the
[Code of Conduct](CODE_OF_CONDUCT.md) first.

## What to add and where

- New widget, tool or UI element: add it to the **Widget screen**.
- Feature showcase (Collapsing AppBar, BottomSheets, Dialogs, etc.): add it to
  the **HomeScreen list**.
- Demo UI: add it to the **Demo UI** section.
- Avoid adding new icons or images unless they are really needed.
- Everything you add must support **dark mode**.

## Local setup

- JDK 17
- Android SDK 35 (`compileSdk = 35`, see `buildSrc`)
- Android Studio, or the Gradle wrapper from the command line:

```bash
./gradlew assembleDebug
```

## Before opening a pull request

Formatting is enforced by [ktfmt](https://github.com/facebook/ktfmt)
(`googleStyle()`, `maxWidth = 100`, applied to every module in the root
`build.gradle.kts`). Format, then run the checks CI runs:

```bash
./gradlew ktfmtFormat
./gradlew ktfmtCheck
./gradlew assembleDebug testDebugUnitTest
```

## Branches and pull requests

1. Open or pick an issue first, so the change is discussed before it is built.
2. Branch from `master`, with a short descriptive name such as
   `fix/123-short-slug` or `feat/123-short-slug`.
3. Keep the pull request focused on one issue, and link it (`Closes #123`).
4. Fill in the pull request template: problem, solution, how to test, risks,
   and the checklist.
5. For UI changes, say which device/API level you tested, in light and dark
   mode, and with gesture and 3-button navigation where relevant. Add
   screenshots.

## Roborazzi golden screenshots

_Placeholder: how to record and verify the golden screenshots will be documented
here (tracked in #245)._

## Reporting security issues

Please do not open a public issue. See [SECURITY.md](SECURITY.md).
