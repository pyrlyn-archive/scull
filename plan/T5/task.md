# T5. Unicode tables

Status: not started

`scull-unicode`: multi-stage width and grapheme-break tables generated from the Unicode data files, one width function shared by the scanner and the grid, mode 2027, and a setting for ambiguous width and Unicode version. Done when `GraphemeBreakTest.txt` passes and emoji ZWJ, variation selectors and combining marks have golden tests.

Execution plan:
1. Evaluate `unicode-width` and `unicode-segmentation` against a per-code-point table (configurable ambiguous width, incremental grapheme state); record the choice in the commit.
2. A generator (workspace tool under `tools/`) reads pinned Unicode data files checked by SHA-256 and writes multi-stage tables into `scull-unicode`, committed; a test fails when the output is stale.
3. `scull-unicode`: `width(cp, opts)`, incremental grapheme-break state, cluster width for mode 2027, ambiguous-width and Unicode-version options.
4. Tests: `GraphemeBreakTest.txt` in full; golden tests for emoji ZWJ, variation selectors and combining marks.
5. Verify with `./gradlew check`. Over the 500-line budget, split the rest into T5.x.

Acceptance:
- [ ] The work described above is implemented in Kotlin/Native (platform UI stays Swift/C# where this task says so).
- [ ] Tests and the `./gradlew check` gate (plus the platform tests this task names) pass.
