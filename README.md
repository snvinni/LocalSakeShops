# Local Sake Shops

A cross-platform mobile app built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform (CMP)** that displays a list of local sake shops and detailed information for each.  
Tapping an address opens the native Maps app, and tapping a website link opens the default browser.

---

## 📋 Features

- **List Screen**  
  - Scrollable list of sake shops  
  - Displays name, address and star rating  

- **Detail Screen**  
  - Shop name, image and description  
  - Star rating component  
  - Tappable address (launches Maps)  
  - “Visit Website” button (opens browser)  

- **Shared Business & Data Layer**  
  - JSON parsing, entities, use-cases and ViewModels live in the `shared` module  

- **Compose UI**  
  - Single codebase for both Android & iOS  
  - Material 3 components for a consistent look  

- **MVVM + Clean Architecture**  
  - Separation of concerns: `domain`, `data` and `ui` packages  

---

## 🗂 Project Structure

```
/
├── shared/            # KMP module (commonMain/commonTest)
│   ├── domain/        # Entities & use-cases
│   ├── data/          # Repositories & JSON parsing
│   └── ui/            # Composables & ViewModels
├── androidApp/        # Android host module
├── iosApp/            # iOS host module
└── list.json          # Sample data in shared resources
```

---

## ⚙️ Prerequisites

- **JDK 11+**  
- **Kotlin 1.8+**  
- **Android Studio Electric Eel** (or newer) with KMM & CMP plugins  
- **Android SDK Platform 33**  
- **Xcode 14+**  
- **CocoaPods** (if using Gradle’s CocoaPods integration)  
- **Git**  

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/local-sake-shops.git
cd local-sake-shops
```

### 2. Common setup

```bash
./gradlew clean assemble
```

Ensure `list.json` is present under `shared/src/commonMain/resources/`.

---

## 🤖 Android Setup

1. **Open in Android Studio**  
   - Choose **Open** → select the `androidApp/` folder  

2. **Sync Gradle**  
   - Click **Sync Now** when prompted  

3. **Run on device/emulator**  
   - Target API 33+ and hit **Run**  

4. **APK location**  
   - `androidApp/build/outputs/apk/debug/androidApp-debug.apk`

---

## 🍎 iOS Setup

1. **Install CocoaPods** (if required):

   ```bash
   cd iosApp
   ./gradlew podInstall
   ```

2. **Open Xcode workspace**  
   - Open `iosApp/iosApp.xcworkspace`  

3. **Select scheme & simulator**  
   - Choose the `iosApp` scheme and iOS 15+ simulator  

4. **Build & Run**  
   - Press ⌘R or click **Run**  

---

## 🧪 Testing

- **Shared unit tests**:

  ```bash
  ./gradlew shared:test
  ```

- **Android unit tests**:

  ```bash
  ./gradlew androidApp:testDebugUnitTest
  ```

- **iOS unit tests**  
  - Run `iosAppTests` from Xcode’s Test navigator  

---

## 🏗 Architecture & Design

- **Single Source of Truth** in `shared` for all business logic  
- **Compose Multiplatform** UI for feature parity and faster iteration  
- **MVVM** pattern with `ViewModel`s in `shared`  
- **Clean separation**:  
  - `data` – network & parsing  
  - `domain` – entities & use-cases  
  - `ui` – Compose screens
  - 
---

## 📬 Contact & License

Feel free to open issues or submit PRs!  
Questions? Reach me at `vi.snascimento07@gmail.com`.  
Licensed under MIT.
