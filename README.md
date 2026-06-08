# Greeting Remote Compose 🚀

An official companion repository demonstrating **Server-Driven UI (SDUI)** on Android using Google's modern **Remote Compose** (`androidx.compose.remote`) framework and Firebase Storage.

This project showcases how to stream native Material 3 canvas drawing primitives directly from the cloud—eliminating fragile custom JSON parsing engines, preventing runtime formatting crashes, and bypassing traditional Play Store release bottlenecks.

---

## 📸 Architectural Concept & Flow

Instead of building a separate standalone backend service, this repository utilizes a single-project architecture driven by a local configuration flag (`val isAdmin: Boolean`). This setup allows you to easily simulate both sides of the network pipeline.

### The Dynamic UI Swap
By swapping the compiled binary file (`main_screen.bin`) in your Firebase Storage console, the client application magically transforms its layout on a simple **Pull-to-Refresh** gesture without recompiling code or forcing an APK update!

<p align="center">
  <img src="images/eid_render.png" alt="Eid Campaign State" width="35%" />
  <img src="images/newyear_render.png" alt="New Year Campaign State" width="35%" />
</p>

---

## ✨ Key Features

* **Zero Component Mapping:** No manual JSON parsing or client-side switch-case registries. Layout structures serialize implicitly.
* **Compile-Time Safe Generation:** Server-side templates use pure Kotlin DSL, ensuring layout structures are mathematically guaranteed to parse safely.
* **Dynamic Native Performance:** Records low-level native drawing commands running effortlessly at 60 FPS with full accessibility and Material 3 support.
* **Edge-to-Edge UI & Interoperability:** Uses `RemoteComposePlayer` loaded dynamically inside a standard Jetpack Compose container via `AndroidView` wrapped in a Material 3 `PullToRefreshBox`.

---

## 🛠️ Tech Stack & Dependencies

* **UI Framework:** Jetpack Compose (Material 3)
* **SDUI Engine:** `androidx.compose.remote` (Core, Creation, and Player modules)
* **Cloud Repository:** Firebase Storage KTX (Binary Blob Delivery)
* **Image Loading:** Coil (Hardware bitmaps deactivated for background canvas capture synchronization)
* **Asynchronous Engine:** Kotlin Coroutines & Reactive StateFlow

---

## 🚀 Getting Started

Follow these steps to set up the environment and test the instant cloud layout swap.

### 1. Prerequisites
* Android Studio Ladybug (or newer)
* Minimum SDK: **23** | Target SDK: **37**
* A free Firebase Account (Spark Tier)

### 2. Firebase Project Initialization
1. Register a new Android App in your Firebase Console matching your local application package identifier.
2. Download your generated `google-services.json` metadata file and place it securely in your `/app` directory module.
3. Open your Firebase **Storage** tab and adjust the security rules to allow unauthenticated read/write access *exclusively* for your target layout folder:

```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    match /layouts/{allPaths=**} {
      allow read, write: if true;
    }
    match /{allPaths=**} {
      allow read, write: if request.auth != null;    
    }
  }
}