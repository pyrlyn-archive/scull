# T1. Done

Pull request: https://github.com/pyrlyn/scull/pull/63

- `research.md` §8 records the core language as decision 8: Kotlin/Native, built with Gradle and the Kotlin Multiplatform plugin for `macosArm64`, `linuxX64` and `mingwX64`. It replaces the Rust core. The lead sentence states that no §8 question is open.
- `research.md` §7 drops the stale "C# / C++" Windows label and the Rust term "panic guard".
- `AGENTS.md` Decisions lists the core language and names Microsoft.Windows.CsWin32 as the Windows interop library (`research.md` §6.1). Its "Read first" line no longer says every task is unstarted.
- `PLAN.md` checks T1 and moves its status to in progress; `plan/README.md` says the same about task folders.

Checked: each of the eight §8 rows has a matching bullet in `AGENTS.md`; `grep -E 'C# / C\+\+|panic guard'` finds nothing in `research.md` or `AGENTS.md`; `mise x -- jactionlint` passes (one finding hidden by the existing baseline, no workflow changed). No code was built: T1 is documentation only, and the Gradle build starts in T2.
