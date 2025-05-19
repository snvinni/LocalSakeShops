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
└── iosApp             # iOS host module
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

---

## 🚧 Roadmap & Future Improvements

1. **Offline caching** for network resilience  
2. **Search & filter** by name, rating, location  
3. **UI tests** (Espresso on Android, XCTest on iOS)  
4. **Error states** & retry UI for failed network calls  
5. **Localization** support  

---


---

## 🖼️ Screenshots

### Android

<img width="324" alt="image" src="https://github.com/user-attachments/assets/ad394bd4-cfde-4fb7-954c-fa2a7ab902f9" />
<img width="331" alt="image" src="https://github.com/user-attachments/assets/ece3b223-2b20-4a9b-b7f9-483983440135" />

### iOS

<img width="330" alt="image" src="https://github.com/user-attachments/assets/1e18e2fe-d83a-419d-84b1-4de5124a2947" />
<img width="332" alt="image" src="https://github.com/user-attachments/assets/0725c4fe-f5f6-4532-a664-14d366ecff92" />

## 📬 Contact & License

Feel free to open issues or submit PRs!  
Questions? Reach me at `vini.snascimento00@gmail.com`.  
Licensed under MIT.
