# Contributing to ComposeCookBook

ComposeCookBook is a gallery of Compose widgets, screens and demos. New work should
follow the existing conventions so that the automated quality gates keep passing.

## Running the quality gates

The four checks in [`.github/workflows/pr-quality.yml`](.github/workflows/pr-quality.yml)
run on every push/PR to `master` and are intended to be registered as required branch
protection checks:

| Job | Command | What it guards |
|---|---|---|
| `unit-tests` | `./gradlew testDebugUnitTest` | JUnit + Robolectric Compose UI + Roborazzi screenshot tests |
| `screenshot-verification` | `./gradlew verifyRoborazziDebug` | Roborazzi golden screenshots |
| `lint` | `./gradlew lintDebug --continue` | Android Lint (incl. Compose lint rules) |
| `ktfmt` | `./gradlew ktfmtCheck` | `ktfmt` googleStyle (max width 100) formatting |

Registering those four job ids as *required status checks* on `master` is a
repository-settings action for the maintainer and is not automated from the workflow.

Before opening a PR, run them locally:

```bash
./gradlew build --continue
./gradlew ktfmtCheck
./gradlew testDebugUnitTest
./gradlew verifyRoborazziDebug
./gradlew lintDebug --continue
```

## Adding or changing a screen

- New demo screens belong in a dedicated module or the `app` Home list; new standalone
  widgets belong under `components/`.
- Use `ComposeCookBookMaterial3Theme` for new Material 3 screens; every screen needs at
  least one `@Preview`.
- Where practical, add a Roborazzi golden (see below) and a Robolectric Compose UI test
  so the screen is covered without a device.
- Keep the code formatted: run `./gradlew ktfmtFormat` before committing.

## Updating Golden Screenshots

Golden screenshots are captured with [Roborazzi](https://github.com/takahirom/roborazzi)
under [Robolectric](https://github.com/robolectric/robolectric) (no device required) and
stored under each module's `src/test/screenshots/` directory. `verifyRoborazziDebug`
compares the rendered output of every test against its committed baseline and fails when
they differ.

To update the baselines after an intentional visual change:

1. Record new goldens for the affected module(s):

   ```bash
   ./gradlew :components:tags:recordRoborazziDebug
   # or all modules at once:
   ./gradlew recordRoborazziDebug
   ```

   `recordRoborazziDebug` overwrites the PNGs under `src/test/screenshots/` with the
   newly rendered images.

2. Review the generated/changed PNGs (they are ordinary files in the tree) and confirm
   the differences are the intended ones.

3. Commit the updated PNGs together with the code change.

4. Verify the full set is consistent with:

   ```bash
   ./gradlew verifyRoborazziDebug
   ```

### Reviewing diffs in CI

When a preview changes but the golden was not re-recorded, the
`screenshot-verification` job fails and uploads per-image diff PNGs as a
`roborazzi-diffs` artifact (under `**/build/outputs/roborazzi/`). Open that artifact to
see exactly which pixels changed before either re-recording the baseline or fixing the
regression.

Screenshots are captured at `w360dp-h640dp` with `@Config(sdk = [35])`, in light and
dark variants where the preview supports them.
