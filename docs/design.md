# UI design

The UI of Scull is designed in Figma: [Modern terminal](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal). The file has one page, **Screens** (`0:1`), with 36 frames of 1440 × 900. This document describes each frame and links to it. Every heading links to its frame; the node id is in brackets so it can be passed to the Figma MCP tools (`get_design_context`, `get_screenshot`) as `nodeId`.

The mockups call the product **Orbit** and use a sample project `orbit-api` (a Node service with billing code, a `prod-eu-1` host and a `staging` database). Read Orbit as Scull and the sample data as placeholders. Several screens show features that `PLAN.md` does not schedule yet (the AI agent, workflows, account sync, block sharing). Those frames are design references, not commitments; building one needs a task in `plan/`.

How to use it:

- UI work on macOS (SwiftUI) and Windows (WinUI 3) starts from the frame that this document names for the screen, not from a fresh layout.
- Take sizes, colours and spacing from the frame with `get_design_context`; take behaviour from the description here.
- When a frame changes in Figma, update its entry here in the same change as the code.

## Shared layout

The main window, as in frame 01, has five regions that most frames reuse:

| Region | Content |
| --- | --- |
| Title bar | Session tabs with their shortcut (`⌘1`…`⌘3`), a new-tab button, the search field that opens the command palette (`⌘K`), and buttons for the right panel and settings |
| Sidebar | Three lists: **Sessions** (local shell, SSH host, database, log tail, each with a status dot), **Workflows** (saved command sequences with shortcuts) and **History** (recent commands with their age); an **Agent ready** card at the bottom |
| Main | A header with the working directory and session meta (`3 blocks · session 12m`); output as **command blocks**; a context chip row (directory, git branch with ahead/dirty counts, runtime versions); the prompt with inline autocomplete |
| Right panel | A per-session tool panel. By default **Monitor** with tabs System, Ports and Logs: CPU with per-core bars, memory (used, cache, swap), a process table sorted by CPU, and network throughput |
| Status bar | Connection state, shell version, git branch, encoding, grid size (`120×38`), the `⌘K` hint and the clock |

A **command block** is the unit of output: a status icon (success, running, failed), the command, a badge (`128 passed`, `running · 00:42`), duration and wall-clock time, then the output. A running block has Stop and **Explain** actions and shows its PID. The autocomplete list mixes an AI suggestion drawn from the git diff with history entries, with key hints (`↑↓` navigate, `⇥` accept, `esc` dismiss).

## Frames

### [00 · Reference (original)](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=1-2) (`1:2`)

The original single-mockup design the other frames were built from: the main window with sessions, workflows, history, command blocks, autocomplete and the Monitor panel, drawn without components. Kept for reference; build from frame 01 onward.

### [01 · Main — macOS](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=74-2) (`74:2`)

The main window on macOS in the dark theme, built from components (`TitleBar`, `Sidebar`, `CommandBlock`, `Prompt`, `Autocomplete`, `Right panel`, `StatusBar`). Traffic-light window buttons on the left, `⌘` shortcuts. The reference for the shared layout above. Three blocks: `git status -sb` (done), `pnpm test` (128 passed) and `pnpm dev` (running, with Stop and Explain 404); the prompt holds a half-typed `git commit` with the autocomplete open.

### [02 · Main — Windows 11](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=74-912) (`74:912`)

The same window on Windows 11: the app icon on the left of the title bar, minimise, maximise and close on the right, and `Ctrl` shortcuts (`Ctrl+1`, `Ctrl K`, `Ctrl+D`) in place of `⌘`. Layout and content match frame 01.

### [03 · Main — Linux (GNOME)](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=74-1456) (`74:1456`)

The same window with GNOME window controls and `Ctrl` shortcuts. Linux has no platform UI in `PLAN.md` yet; the frame keeps the layout consistent if one is added.

### [04 · Command palette (⌘K)](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=75-1180) (`75:1180`)

The palette over the main window. A search field (query `prod`), filter badges by kind, and results grouped into **Hosts**, **Workflows** and **Commands**, each item with its shortcut. The footer shows key hints and the result count (`12 results`). One palette searches commands, history, hosts and workflows.

### [05 · SSH session — prod-eu-1](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=75-1824) (`75:1824`)

A session on a production host. A **production banner** says destructive commands ask for confirmation. An inline **confirm card** for `sudo systemctl restart orbit-api` explains the impact (about 4 s downtime, 2 SSE connections reconnect) before it runs. The right panel becomes **Host**: connection (address, user, port, key, jump host, latency), remote resources (CPU, memory, disk), port forwards, and snippets saved for this host.

### [06 · Database — psql staging](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=75-2341) (`75:2341`)

A `psql` session on the staging database. The right panel becomes **Schema**: a filter field, the tables of `public`, the columns of the selected table (`invoices`: name and type) and saved queries.

### [07 · Hosts & connections](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=77-2084) (`77:2084`)

The host manager. On the left, a searchable host list grouped into Production, Staging and Local with counts. On the right, the selected host (`prod-eu-1`: OS, region, last connection) with actions, a **Connection** form (name, host, port, user, authentication, jump host) and **Behaviour** toggles.

### [08 · AI agent — natural language](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=77-2666) (`77:2666`)

A prompt line starting with `#` is a request to the agent (`find what's listening on 5173 and kill it`). The agent answers with a **plan block**: what it used as context, an explanation, and numbered commands with a reason each (`lsof …`, `kill -TERM 48211` falling back to SIGKILL after 5 s). The right panel becomes **Agent**: the context sent (working directory, git diff, last blocks with a token estimate, Monitor data, environment variables never), safety toggles (ask before running, allow destructive commands), the model, and recent asks.

### [09 · AI agent — explain & fix error](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=77-3030) (`77:3030`)

**Explain** on a failed block. The right panel becomes **Explain error**: what happened in plain words, a suggested fix as a diff of the named file (`src/billing/pdf.ts`), other options with their trade-offs, and a "Was this helpful?" prompt.

### [10 · Workflows — editor](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=79-2954) (`79:2954`)

Editing the workflow **Deploy to staging**. The main area has the title and description, **parameters** (branch, environment, image tag, run migrations) and six numbered **steps**, each a shell command. The right panel becomes **Workflow**: triggers (manual with `⌘D`, on push to `main`, on a schedule), where it runs, secrets (masked) and recent runs with their result.

### [11 · Workflows — run in progress](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=79-3446) (`79:3446`)

Run #142 of the workflow. A summary line (`Running · step 3 of 6`, branch, image, target, ETA) above the live output of the current step. The right panel shows run details (start, trigger, runner, commit, approvals), runner load, and what to do when it finishes (desktop notification, post to a channel, open a URL). A toast reports progress.

### [12 · Settings — Appearance](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=80-3436) (`80:3436`)

The settings window: a searchable section list on the left with the version and update state at the bottom. **Appearance** holds the theme (Orbit Dark, Orbit Light, Nord, Solarized), the accent colour (used for focus, running blocks and the agent) and typography. A **live preview** on the right shows a sample block and the ANSI palette; changes apply live.

### [13 · Settings — Keybindings](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=80-3854) (`80:3854`)

**Keybindings**, grouped into General, Terminal and Agent, with search and filter badges. Clicking a shortcut records a new one (`Press keys…`). A conflict is flagged on the row (split pane right against the workflow's `⌘D`).

### [14 · Monitor — Ports](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=81-3854) (`81:3854`)

The **Ports** tab of Monitor. Listening ports with the owning process and where it came from (this session, Docker, a Homebrew service, the debugger), SSH forwards with the host they go through, and an inline confirmation to stop a port's process that names the block it will end.

### [15 · Monitor — Logs](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=81-4432) (`81:4432`)

The **Logs** tab: a source picker, level badges, a "following" indicator and a live log stream. One line is expanded to show its stack trace and request metadata, with actions on it.

### [16 · Split panes](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=81-5015) (`81:5015`)

Three panes in one window: the local shell on the left, and on the right `journalctl -f` on `prod-eu-1` above a `psql` session. Each pane has a header with a status dot and its title. A hint explains dragging to resize and the modifier plus arrows to move focus.

### [17 · New session — empty state](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=82-4720) (`82:4720`)

A new session before the first command: "Ready when you are" with the directory, shell, runtime and branch. Quick actions (run a workflow, connect to a host, ask the agent, split pane), scripts detected in `package.json`, and recent directories.

### [18 · SSH — connection lost](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=82-5062) (`82:5062`)

A dropped SSH session. A banner counts down to the next reconnect. The blocks stay visible but frozen, and a **reconnect card** shows when the host was last seen, the reason (`read: connection timed out`) and the backoff (2 s up to 30 s). Scrollback is kept.

### [19 · Main — Light theme](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=82-5409) (`82:5409`)

Frame 01 in the light theme (Orbit Light). The reference for light colours of every region and block state.

### [20 · Onboarding — first launch](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=88-5431) (`88:5431`)

A four-step first-launch flow: Welcome, Import shell setup, Pick a theme, Set up the agent. The import step lists what was found and reads it without changing the originals: `~/.zshrc` (aliases, functions, framework), `~/.ssh/config` (hosts, jump hosts), shell history and iTerm2 profiles, plus the default shell.

### [21 · Find in output & block menu](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=88-5922) (`88:5922`)

A **find bar** over the output (query `invoice`, case and regex toggles, `1 of 12`, previous and next) with matches highlighted in the blocks. A block's **context menu**: copy command, copy output, rerun, share block, bookmark, explain with the agent, and delete block, each with its shortcut. A tooltip on a title bar button shows its name and shortcut.

### [22 · Share block](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=88-6556) (`88:6556`)

A modal that shares one block as a link. Who can view (the team with sign-in, or anyone with the link, read-only), whether to include the environment and the full output (`2,140 lines`), and who has viewed it with the link's expiry.

### [23 · History — search](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=88-7121) (`88:7121`)

Full command history as a searchable table (command, where it ran, duration or exit code, time) grouped by day, with the result count. The right panel shows **Insights · 7 days**: metric cards and the top commands with their counts.

### [24 · Settings — AI agent](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=89-6397) (`89:6397`)

**AI agent** settings: models, behaviour, and context & privacy. The plan always shows before anything runs. The right side shows usage this month and the **redaction patterns** that are never sent (`*_KEY`, `*_TOKEN`, `*_SECRET`, `DATABASE_URL`, `~/.aws/credentials`).

### [25 · Settings — Terminal profiles](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=89-7081) (`89:7081`)

**Terminal**: shell profiles, scrollback and bell, in tabs. A profile list and the profile form: command, working directory and environment variables as key–value rows.

### [26 · Settings — Account & sync](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=89-7439) (`89:7439`)

**Account**: the signed-in profile, what syncs across devices, and the device list with OS, app version and last sync.

### [27 · SSH — verify host & passphrase](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=90-7343) (`90:7343`)

A modal on first connection to a host. The host key fingerprint (`ED25519`, SHA256) with a copy button, "Remember this host in known_hosts", the passphrase field for the key, and an option to save the passphrase in the platform keychain. The footer names the jump host the connection goes through.

### [28 · Quit — running processes](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=90-7910) (`90:7910`)

A quit confirmation that lists what is still running and will stop: a dev server, an SSH session with active port forwards, and a workflow run, each with a status dot. "Restore tabs and scrollback on next launch", and the hint that pressing `⌘Q` again forces the quit.

### [29 · About & updates](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=90-8438) (`90:8438`)

The About modal: version, build date, architecture and channel; an update banner; the changelog of the next version; the update channel picker and the automatic update toggle.

### [30 · Glass — macOS Liquid Glass](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=108-8212) (`108:8212`)

Frame 01 with a translucent window over a wallpaper, using the macOS 26 Liquid Glass material for the window, sidebar and panels.

### [31 · Glass — Windows 11 Acrylic](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=108-8681) (`108:8681`)

Frame 02 with the Windows 11 Acrylic material over a wallpaper.

### [32 · Glass — Linux frosted blur](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=108-9160) (`108:9160`)

Frame 03 with a frosted blur behind the window (KDE and GNOME).

### [33 · Glass — macOS Liquid Glass (Light)](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=108-9637) (`108:9637`)

Frame 30 in the light theme.

### [34 · Glass — Command palette](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=108-10106) (`108:10106`)

Frame 04 over the glass window: how the palette reads above a translucent background.

### [35 · Settings — Window material](https://www.figma.com/design/tCFCAuDJvskm2XTh0cOLcX/Modern-terminal?node-id=109-9719) (`109:9719`)

**Window material** settings over a wallpaper. Material choice: Solid (no transparency), Liquid Glass (macOS 26), Acrylic (Windows 11) or Frosted (Linux, KDE and GNOME), using the native material of the platform. Fine-tune sliders for opacity (`58%`, how much of the desktop shows through) and blur (`48 px`, higher reads easier). A live preview shows the result.
