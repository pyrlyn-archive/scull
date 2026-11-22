# T2. Workspace scaffold

Status: not started

Gradle Kotlin Multiplatform workspace with the seven modules of `research.md` §7, a pinned JDK and Kotlin/Native toolchain, ktlint, compiler warnings as errors, CI, and `toolchain.md` filled in. Done when an empty Gradle workspace builds and lints clean on macOS, Linux and Windows.

Targets: `macosArm64` (primary), `linuxX64`, and `mingwX64` for the core library the Windows host loads. The C ABI module (`scull-ffi`) produces a static or shared library for the Swift and C# apps.

Acceptance:
- [ ] The seven modules exist, compile as Kotlin/Native, and lint clean.
- [ ] `./gradlew check` is the merge gate and is documented in `README.md` and `toolchain.md`.
