> [!NOTE]
> This project is under active development. We need "testing volunteers": try it and report what breaks.

# Scull

A terminal emulator with a Kotlin/Native core and a native interface on each platform: SwiftUI on macOS, WinUI on Windows. The core parses, keeps state and encodes input; the platform draws text with its own text stack and handles windows, input methods and accessibility.

Status: early development, no release yet. The project is being rebuilt in **Kotlin/Native**. The implementation plan is in [`PLAN.md`](PLAN.md); task folders are in [`plan/`](plan/README.md). The SwiftUI app lives in [`macos/`](macos/) and the WinUI app in [`windows/`](windows/). They will link the core through the same C ABI (`scull.h`) once the core is built.

```bash
mise install        # JDK 21 and the lint tools from mise.toml
./gradlew check     # format, lint and test the Kotlin/Native core (from T2)
```

- [docs/](docs/README.md) — benchmarks, research notes and the config schema.
- [research.md](research.md) — how kitty, WezTerm, Alacritty, foot, Contour and Warp work, and what Scull takes from each.
- [plan/](plan/README.md) — tasks, one folder each. All tasks were reset and are not started.
- [roadmap.md](roadmap.md) — approved later work.

## License

You can use this project under **any** of the following licenses, at your choice:

1. [GNU GPLv3](LICENSE): free for open source applications on any platform, including embedded systems.
2. [Royalty-free License](LICENSE-ROYALTY-FREE.md): free for proprietary desktop, mobile, and web applications, as long as you disclose that your application uses this project. Embedded systems are not covered.
3. [Commercial license](PRICING.md): for proprietary applications, including embedded systems, without the attribution requirement.

<!-- license-sync:start -->
Commercial use not covered by the GPLv3 or the Royalty-free License requires a separate paid
license — see [PRICING.md](PRICING.md).
<!-- license-sync:end -->
