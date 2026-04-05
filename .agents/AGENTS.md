# MyFeed - Kotlin Multiplatform Project Context

This document provides essential context and instructions for the MyFeed project, a minimalist YouTube feed application built with Kotlin Multiplatform (KMP).

## 🎯 Project Overview
MyFeed is an immersive, auto-scrolling vertical video feed for YouTube URLs. It allows users to curate a playlist of URLs (Live and VOD) and consumes them in a distraction-free environment.

- **Primary Targets:** Android & iOS.
- **UI Framework:** Compose Multiplatform.
- **Architectural Pattern:** Modular, UDF (Unidirectional Data Flow) with Flow and ViewModels.

## 🛠 Tech Stack
- **Languages:** Kotlin (Multiplatform), Swift (for iOS entry point).
- **Video Playback:**
    - Android: Media3 (ExoPlayer).
    - iOS: AVPlayer (AVKit).
- **Persistence:** Room 3.0 (KMP) with SQLite.
- **Navigation:** Navigation 3 (Compose Multiplatform).
- **UI/UX:** Material 3, utility-first design, edge-to-edge experience.
- **Build System:** Gradle (Kotlin DSL) with custom convention plugins in `build-logic`.

## 🏗 Project Structure
- `composeApp/`: Core application module containing shared UI and logic.
    - `src/commonMain/`: Shared Compose UI and business logic.
    - `src/androidMain/`: Android-specific implementations (e.g., Media3 player).
    - `src/iosMain/`: iOS-specific implementations (e.g., AVPlayer interop).
- `iosApp/`: Native iOS entry point and SwiftUI wrapper.
- `build-logic/`: Custom Gradle convention plugins for consistent module configuration.
- `core/`: (Planned/In-Progress) Shared infrastructure modules.
    - `designsystem/`: Material 3 themes and common components.
    - `database/`: Room persistence layer.
    - `player/`: Media player abstractions and implementations.
- `feature/`: (Planned) Feature-specific modules (e.g., `config`, `feed`).

## 🚀 Building and Running
### Android
Build and run the development version:
```shell
./gradlew :composeApp:assembleDebug
```

### iOS
Build and run the development version:
- Open the `iosApp` directory in Xcode and run the project.
- Or use the Gradle task (if configured for your IDE):
```shell
./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
```

## 📏 Development Conventions
- **Shared First:** Favor `commonMain` for all logic and UI unless platform-specific APIs are strictly required.
- **Convention Plugins:** Use the custom plugins defined in `build-logic` (e.g., `myfeed.kmp.library`, `myfeed.compose.multiplatform`) for new modules.
- **Persistence:** Use Room 3.0 for all local data. Definitions should be in `commonMain` with builders in platform-specific sets.
- **Media Player:** Implement the `MyFeedPlayer` abstraction (defined in roadmap) to bridge Media3 and AVPlayer.
- **Static Analysis:** The project uses **Detekt** and **Spotless** for code quality and formatting. Run them using:
  ```shell
  ./gradlew detekt check
  ```
- **Documentation:** Refer to `docs/PRD.md` for product requirements and `docs/ROADMAP.md` for the current development phase.

