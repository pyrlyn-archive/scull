---
status: in progress
priority: P0
complexity: 1
readiness: 10%
agent: Claude Code / claude-opus-5-5
---

# T1. Settle the open decisions

Answer the open questions of `research.md` §8; T1 blocks all other work. The earlier answers still stand, with one stack change: the VT core is our own Kotlin/Native code, not Rust and not libghostty-vt. Record the decisions in `AGENTS.md`.

Acceptance:
- [ ] Every §8 question is answered and written down in `AGENTS.md` and `research.md` §8.
- [ ] The core language is recorded as Kotlin/Native.

## Execution plan

1. `research.md` §8: add the core language (Kotlin/Native, Gradle Kotlin Multiplatform, the three targets) as its own decision row and name it in the lead sentence.
2. `research.md` §7: drop the stale "C# / C++" host label (decision 2 is all C#) and the Rust word "panic guard".
3. `AGENTS.md` Decisions: add the core language and name the Windows interop library T17 already picked (CsWin32, `research.md` §6.1).
4. Verify: every §8 row has a matching line in `AGENTS.md`; `grep` finds no Rust-era wording in the decision text; `mise x -- jactionlint` still passes (no workflow touched).
