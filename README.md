# 🌦️ WeatherSnap

A modern Android weather intelligence and reporting application built with **Kotlin**, **Jetpack Compose**, **MVVM Architecture**, **Retrofit**, **Room Database**, and **CameraX**.

WeatherSnap combines real-time weather tracking with environmental field reporting capabilities, allowing users to:

* search cities globally,
* view live weather conditions,
* capture weather evidence using the camera,
* attach field notes,
* and save detailed weather reports locally.

The application focuses on a sleek modern dashboard experience inspired by premium dark-themed interfaces and operational reporting systems.

---

# ✨ Features

## 🌍 Smart City Search

* Real-time city suggestions
* Powered by Open-Meteo Geocoding API
* Dynamic dropdown search results
* Duplicate city filtering

---

## ☁️ Live Weather Data

Fetches real-time weather information including:

* Temperature
* Weather condition
* Humidity
* Wind speed
* Atmospheric pressure

---

## 📸 Camera Integration

Built with **CameraX** for:

* in-app weather evidence capture
* instant preview support
* persistent image storage
* stable URI handling using FileProvider

---

## 📝 Weather Reporting System

Users can create detailed reports including:

* live weather snapshot
* captured image
* custom field notes
* timestamped entries

---

## 💾 Local Storage with Room

All reports are stored locally using:

* Room Database
* Kotlin Coroutines
* Flow API

Reports persist across app restarts.

---

## 🎨 Modern UI/UX

* Dark futuristic dashboard design
* Rounded glass-inspired cards
* Compact information hierarchy
* Responsive Compose layouts
* Dynamic metric color system

---

# 🏗️ Tech Stack

| Technology         | Purpose                   |
| ------------------ | ------------------------- |
| Kotlin             | Primary language          |
| Jetpack Compose    | Modern UI toolkit         |
| MVVM               | Architecture pattern      |
| Retrofit           | API networking            |
| Gson               | JSON parsing              |
| Room Database      | Local persistence         |
| Kotlin Coroutines  | Async operations          |
| StateFlow          | Reactive state management |
| Navigation Compose | Screen navigation         |
| CameraX            | Camera integration        |
| Coil               | Image loading             |
| Open-Meteo API     | Weather & geocoding       |

---

# 📱 Screens

## 🔎 Weather Dashboard

* Search city
* View live weather
* Access report creation

---

## 📷 Create Report Screen

* Weather snapshot overview
* Capture photo
* Add field notes
* Save reports locally

---

## 🗂️ Saved Reports

* View all stored reports
* Display captured weather evidence
* Timestamped history
* Persistent weather metrics

---

# 🧠 Architecture

The project follows a clean **MVVM architecture**:

```text
UI (Compose)
↓
ViewModel
↓
Repository / API / Database
↓
Room + Retrofit
```

### Benefits

* scalable structure
* maintainable codebase
* reactive UI updates
* separation of concerns

---

# 🌐 API Used

## Open-Meteo APIs

### Geocoding API

Used for city search suggestions.

### Forecast API

Used for current weather data.

---

# 🔐 Permissions

The app requires:

```xml
CAMERA
INTERNET
```

---

# 📂 Project Structure

```text
com.project.weathersnap
│
├── data
│   ├── local
│   └── remote
│
├── navigation
│
├── ui
│   ├── weather
│   ├── report
│   ├── saved
│   └── camera
│
├── viewmodel
│
└── utils
```

---

# 🚀 Future Improvements

Planned enhancements include:

* Dynamic weather animations
* GPS current location support
* Offline caching
* Weather forecast charts
* PDF export for reports
* Cloud synchronization
* Firebase authentication
* AI-generated weather summaries
* Environmental incident classification
* Report sharing system

---

# ⚙️ Setup Instructions

## 1️⃣ Clone Repository

```bash
git clone <repository-url>
```

---

## 2️⃣ Open in Android Studio

Recommended:

* Android Studio Hedgehog or newer

---

## 3️⃣ Sync Gradle

Allow Gradle dependencies to download.

---

## 4️⃣ Run Application

Use:

* Physical Android device
  OR
* Android Emulator

Minimum SDK:

```text
Android 8.0+
```

---

# 📸 Image Handling

The application uses:

* FileProvider
* Cache storage
* AsyncImage (Coil)

to ensure:

* stable image persistence
* modern scoped storage compatibility
* reliable rendering

---

# 🧪 Key Learning Areas Demonstrated

This project demonstrates:

* Modern Android development
* Declarative UI with Compose
* State management
* API integration
* Local database persistence
* Camera integration
* Navigation architecture
* Reactive programming
* Clean UI design systems

---

# 👨‍💻 Developer

**Aditya Chatterjee**

* Information Technology Engineering Student
* Android & Backend Developer
* Hackathon Finalist
* Java/Kotlin Enthusiast

---

# 📜 License

This project is intended for:

* educational purposes
* portfolio demonstration
* Android development practice

---

# 🌩️ WeatherSnap

> “Not just a weather app. A field-ready atmospheric reporting system.”
