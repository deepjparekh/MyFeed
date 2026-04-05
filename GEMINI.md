# Gemini Project Context: MyFeed

## Project Overview
**MyFeed** is a Kotlin Multiplatform (KMP) mobile application targeting **Android** and **iOS**. It utilizes **Compose Multiplatform** for a shared UI across both platforms.

### Core Technologies
- **Kotlin**: Primary programming language.
- **Compose Multiplatform**: UI framework for shared code.
- **Material 3**: UI design system.
- **Gradle**: Build system.

### Architecture
The project follows the standard KMP structure:
- **`composeApp/`**: Contains the majority of the application logic and UI.
    - `src/commonMain`: Shared code for all platforms (UI, business logic).
    - `src/androidMain`: Android-specific implementations and configurations.
    - `src/iosMain`: iOS-specific implementations.
- **`iosApp/`**: Native iOS entry point, using SwiftUI to wrap the shared Compose UI.

## Building and Running

### Android
- **Build Debug APK**:
  ```bash
  ./gradlew :composeApp:assembleDebug
  ```
- **Run on Device/Emulator**: Use the Android Studio run configuration or:
  ```bash
  ./gradlew :composeApp:installDebug
  ```

### iOS
- **Build/Run**: Open the `iosApp` directory in Xcode and run the `iosApp` target.
- **Gradle Task**: You can also use Gradle to build the iOS framework:
  ```bash
  ./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
  ```

### Testing
- **Run Common Tests**:
  ```bash
  ./gradlew :composeApp:testCommonMain
  ```
- **Run Android Tests**:
  ```bash
  ./gradlew :composeApp:testDebugUnitTest
  ```

## Development Conventions
- **Shared UI**: Prioritize building UI in `composeApp/src/commonMain` using Compose Multiplatform.
- **Platform-Specific Logic**: Use the `expect`/`actual` mechanism or interfaces with platform-specific implementations in `androidMain` and `iosMain`.
- **Dependencies**: Managed via `gradle/libs.versions.toml`. Always check this file before adding new libraries.
- **Resources**: Managed through the Compose Resources library in `composeApp/src/commonMain/composeResources`.

## Key Files
- `composeApp/src/commonMain/kotlin/com/deepjparekh/myfeed/App.kt`: The main entry point for the shared UI.
- `composeApp/build.gradle.kts`: Gradle configuration for the shared module.
- `gradle/libs.versions.toml`: Centralized dependency management.
- `iosApp/iosApp/iOSApp.swift`: Native iOS application entry point.
