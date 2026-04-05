# Product Requirements Document (PRD): MyFeed

**Document Version:** 1.0
**Status:** Draft
**Target Platforms:** Native Android & Native iOS
**UX Link:** https://stitch.withgoogle.com/projects/7838322622706508906

---

## 1. Product Overview

### Objective
Develop a minimalist, native mobile application that allows users to configure a custom playlist of YouTube URLs (Live and VOD) and consumes them in an immersive, auto-scrolling vertical feed.

---

## 2. Scope & Core Functionality (MVP)

The V1 scope is strictly limited to a two-screen architecture focusing on configuration and playback.

* **Configurable Playlist:** Add, remove, and view a list of YouTube URLs.
* **Custom Auto-Scroll Timer:** Global setting defining an absolute duration for how long a video plays before automatically swiping to the next item. Users can disable this feature entirely via the config page, in which case the current video will play in a continuous loop until manually swiped.
* **Vertical Video Feed:** Full-screen, snap-to-page vertical scrolling interface (similar to Shorts/Reels).
* **Seamless Playback:** Auto-play upon focus, pause on blur, loop playlist when reaching the end.

---

## 3. User Experience (UX) & UI Specifications

The design philosophy is utility-first and distraction-free.

### Screen 1: Feed Screen (Home/Launch State)
* **Layout:** Edge-to-edge full screen with immersive Status bar and navigation bar experience.
* **Interaction:** * *Swipe Up/Down:* Manually navigate to the next/previous video (resets the auto-scroll timer).
  * *Tap on video feed item:* Toggle Pause/UnPause feed item video and auto-scrolling.
  * *Tap on mute/un-mute button:* Toggle Mute/Unmute globally across the feed.
* **Visual Indicators:** Minimalist loading spinner during player initialization. No visible playback controls (scrubbers, play/pause buttons) to maintain the "feed" aesthetic.
* * **Launch Action:** If no videos links are persisited and available, shows Add video to feed button, which on tap would navigate to configuration page.


### Screen 2: Configuration Screen
* **URL Input Field:** Text field accepting standard YouTube URLs, shortened `youtu.be` links, and Live stream links. Playlist URLs are explicitly rejected.
* **Add Button:** Validates the URL format and appends it to the state list.
* **Interval Configuration:** Numeric input (in seconds) defining the auto-scroll delay. *Default: 15 seconds.*
* **Active Playlist View:** A dynamic list displaying added URLs. Each row contains the URL string and a "Remove" (trash icon) action.

---

## 4. Technical Architecture & System Design

### 4.1 Persistence
* Settings, the video feed URL list, and the global mute state must be persisted locally so the user does not have to rebuild their feed on cold boots.

### 4.2 Auto-Scroll Mechanism
* Auto-Scroll behavior should be app lifecycle-aware.
* The timer must be strictly tied to the app's foreground state, pause when app goes to background and resume when it comes to foreground.
* Playback and the auto-scroll timer must pause if the application loses system audio focus (e.g., incoming phone call, alarm) or experiences other system interruptions.
* Scroll should be smooth and snap to next item.
* Manual scroll via user should only allow smooth snapping to next item.
* Scroll back to first item in circular fashion once the user is manually/auto-scrolling past last item.

### 4.4 Error Handling
* Invalid URLs, region-locked videos, or disabled embeds should not crash the app.
* The item should display a graceful error state (e.g., "Video Unavailable") and immediately trigger the auto-scroll timer to skip to the next valid video.

---

## 5. User Flows

1.  **Onboarding/Setup:** User opens app -> If no videos perisisted and available, show empty video feed state with button which would lands on Config Screen -> Pastes URL -> Taps Add -> Enters '30' for interval -> Taps 'Start Feed'.
2.  **Consumption:** Pager initializes -> Video 1 auto-plays -> Timer counts to 30s -> Pager programmatically smooth-scrolls to Video 2 -> Video 2 initializes and auto-plays.
---

## 6. Out of Scope for V1
* User authentication or accounts.
* Cloud syncing of playlists.
* Fetching YouTube thumbnails/titles via the YouTube Data API (to keep initial setup API-key free).
* Complex video controls (scrubbing, speed adjustment).
* Playlist reordering (e.g., drag-and-drop to reorder items).
