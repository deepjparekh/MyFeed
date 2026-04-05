# MyFeed: KMP Development & Architectural Roadmap

This roadmap outlines the modular development of the MyFeed application. Each task is designed as an atomic unit suitable for an individual Pull Request (PR).

## Phase 1: Foundation & Core Infrastructure
**Goal:** Establish the project's skeleton, build system updates, and persistent storage layer.
**Modules:** `:core:database`, `:core:designsystem`, `:core:common`

- [x] **PR 1: Version Catalog & Build Logic Setup**
    - Update `libs.versions.toml` with Room 3.0, Media3, Navigation 3, and Lifecycle 2.8+ versions.
    - Implement shared Gradle convention plugins for KMP modules (Android/iOS targets, Kotlin options).
- [x] **PR 2: Core Design System (`:core:designsystem`)**
    - Define Material 3 Color Schemes, Typography, and Shapes.
    - Implement common components: `MyFeedButton`, `MyFeedTextField`, `LoadingSpinner`.
- [ ] **PR 3: Core Database (`:core:database`) with Room KMP**
    - Configure Room 3.0 with `sqlite-bundled` and KSP.
    - Define `VideoEntity` and `SettingsEntity`.
    - Implement `VideoDao` (Add, Remove, Get All) and `SettingsDao` (Mute state, Timer interval).
    - Implement `actual` database builders for Android and iOS.

## Phase 2: Video Player Engine
**Goal:** Create a unified media abstraction for platform-specific players.
**Modules:** `:core:player`

- [ ] **PR 4: Video Player Abstraction**
    - Define `expect` interface `MyFeedPlayer` with methods: `prepare(url)`, `play()`, `pause()`, `release()`, `setMute(isMuted)`.
    - Define `PlayerState` sealed class (Idle, Loading, Ready, Ended, Error).
- [ ] **PR 5: Android Media3 Implementation (`androidMain`)**
    - Implement `actual class Media3Player` using `ExoPlayer`.
    - Create a `ComposePlayerSurface` wrapper using Media3’s `PlayerView`/`PlayerSurface`.
- [ ] **PR 6: iOS AVPlayer Implementation (`iosMain`)**
    - Implement `actual class AVKitPlayer` using `AVPlayer`.
    - Create `UIKitInteropView` to host `AVPlayerLayer` for Compose rendering.

## Phase 3: Feature Implementation - Configuration
**Goal:** Build the playlist management and settings interface.
**Modules:** `:feature:config`

- [ ] **PR 7: Domain Layer & Repository**
    - Create `PlaylistRepository` and `SettingsRepository` implementations interacting with `:core:database`.
    - Implement YouTube URL validation logic (rejecting playlists).
- [ ] **PR 8: Configuration UI & ViewModel**
    - Implement `ConfigViewModel` using KMP-supported `androidx.lifecycle.ViewModel`.
    - Build the Config Screen UI: URL input, scroll interval input, and the active playlist list with delete actions.
    - Implement state persistence for the global mute toggle and timer duration.

## Phase 4: Feature Implementation - Video Feed
**Goal:** Build the immersive, auto-scrolling vertical feed.
**Modules:** `:feature:feed`

- [ ] **PR 9: Feed Pager Implementation**
    - Implement the core UI using Compose's `VerticalPager`.
    - Integrate `:core:player` into the pager items, ensuring "snap-to-page" behavior.
- [ ] **PR 10: Lifecycle-Aware Auto-Scroll Manager**
    - Create `AutoScrollTimer` using Kotlin Coroutines (Flow/Timer).
    - Implement logic to pause/resume the timer based on `LifecycleEventObserver`.
    - Handle system interruptions (audio focus loss) to pause playback and timer.
- [ ] **PR 11: Feed Playback & Resource Management**
    - Implement "Active Page" logic: Only initialize the player for the visible and adjacent indices.
    - Handle circular scrolling (loop back to index 0).
    - Implement error states (Video Unavailable) that trigger an immediate skip.

## Phase 5: App Integration & Navigation
**Goal:** Connect the features and finalize the native entry points.
**Modules:** `:composeApp`

- [ ] **PR 12: Navigation 3 Setup**
    - Implement state-based navigation using `Navigation 3`.
    - Define routes: `Feed` and `Config`.
    - Implement the "Launch Action" logic (Redirect to Config if no URLs exist).
- [ ] **PR 13: Final Integration & Edge-to-Edge UI**
    - Apply `safeContentPadding` and edge-to-edge configurations for Android (Activity) and iOS (ViewController).
    - Wire up the global Mute button on the Feed screen.
- [ ] **PR 14: Final Polish & iOS Interop**
    - Ensure smooth framework bridging in `iosApp`.
    - Final validation of persistence and memory management during long-duration playback.

---

### Technical Constraints Reference
*   **State Management:** Flow-based UDF.
*   **Threading:** Coroutines with `Dispatchers.Main` for UI and `Dispatchers.IO` (wrapped for KMP) for DB operations.
*   **Memory:** Strict `release()` calls on player instances during pager recycling.
