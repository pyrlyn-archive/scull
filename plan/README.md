# Scull

https://github.com/pyrlyn/scull

A terminal emulator with a Kotlin/Native core and a native UI per platform: SwiftUI on macOS, WinUI on Windows, joined by a C ABI. The core computes, the platform renders. The design takes the best part of kitty, WezTerm, Alacritty, foot, Contour and Warp at each pipeline stage; see `research.md`.

Every task in this folder was reset for the rewrite. A closed task has a `done.md`; the rest are not started unless their front matter says otherwise. The phased plan, architecture and acceptance criteria are in [`PLAN.md`](../PLAN.md).
