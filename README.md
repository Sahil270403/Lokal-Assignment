## 📱 Lokal Job App – Android Intern Assignment

This is an Android application built as part of the Lokal Android Intern Assignment.

### 📽️ Video Walkthrough
[▶️ Watch Demo on Loom](https://www.loom.com/share/9c0f6c82869044bc99fcb6342344d950?sid=a5699da5-6538-4706-b870-e74d404d4f81)

### 📦 APK Download
[📥 Download APK](https://drive.google.com/file/d/1y5vCft45667cqamkz6q9l9dTjIN3eE5h/view?usp=sharing)

---

### 🚀 Features

- 🧭 **Bottom Navigation** with two tabs: `Jobs` and `Bookmarks`
- 📄 **Jobs List Screen** with infinite scrolling (pagination)
- 🔍 **Job Detail Screen** with expanded job information
- ⭐ **Bookmark feature** to save jobs locally
- 📴 **Offline Support** using Room database
- 🔄 State handling for loading, empty, and error conditions

---

### 🛠️ Tech Stack

- **Kotlin + Jetpack Compose** – Modern declarative UI
- **Room** – Local database for offline bookmarks
- **Koin** – Dependency Injection
- **MVVM** – Clean architecture pattern
- **Coroutines + Flow + StateFlow** – Reactive programming
- **Retrofit** – API consumption
- **Coil** – Image loading (if applicable)

---

### 🌐 API

- **Endpoint**: [https://testapi.getlokalapp.com/common/jobs?page=1](https://testapi.getlokalapp.com/common/jobs?page=1)

---

### ✅ Features Implemented

- [x] Bottom Navigation with "Jobs" & "Bookmarks"
- [x] Infinite Scroll using pagination
- [x] Job Detail Screen
- [x] Bookmarking and persisting jobs
- [x] Offline viewing of bookmarks
- [x] Proper handling of loading, empty, and error states
