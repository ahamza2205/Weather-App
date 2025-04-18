# ☁️ Weather Now & Later

An Android application that shows the **current weather** and a **7-day forecast** for any city you search for. The app is designed with a **modular architecture**, clean code practices, and supports both **MVVM** and **MVI** architectural patterns.

---

## 🌟 Features

- 🔍 Search for any city and view its weather.
- 🌤️ Display current weather (temperature, condition, icon).
- 📅 View a 7-day weather forecast.
- 💾 Stores last searched city and reloads it on app start.
- 🌙 Supports Dark Mode.

---

## 🧱 Architecture & Layers

### 🔸 Clean Architecture

- **Presentation** – Jetpack Compose UI + ViewModels/State
- **Domain** – Use cases
- **Data** – Repositories, local (Room) & remote (Retrofit) sources


### 🔸 Patterns Used

| Feature            | Pattern |
|-------------------|---------|
| City Input         | MVVM    |
| Current Weather    | MVVM    |
| 7-Day Forecast     | MVI     |

---

## 📦 Modularization

- `:app` → Entry point
- `:core` → Common utilities (networking, utils)
- `:data` → Data sources, repositories
- `:features:cityinput` → City input screen
- `:features:currentweather` → Current weather screen
- `:features:forecast` → Forecast list screen

---

## ⚙️ Tech Stack

- 🧠 MVVM & MVI architectures
- 🧼 Clean Architecture & SOLID principles
- ✏️ Jetpack Compose for UI
- 🔁 Kotlin Coroutines + Flow
- 🌐 Retrofit for networking
- 💉 Dagger-Hilt for Dependency Injection
- 🏠 SharedPreferences for local database
- ✅ JUnit, Mockito for testing
- 🚀 GitHub Actions for CI/CD

---

## 🧪 Testing

- 80%+ unit test coverage
- Mocking using [MockK / Mockito]
- Tested: ViewModels, UseCases, Repositories, Reducers

---

## 🌙 Dark Mode Support

The app fully supports **Dark Mode** 🌑 using Jetpack Compose’s automatically switch themes based on the system preference. All UI components are styled to look great in both light and dark modes.

---

## 🚀 CI/CD Pipeline

Using **GitHub Actions**:
- ✅ Lint Check
- ✅ Unit Test Execution
- ✅ Build Debug APK

---

## ▶️ How to Run

1. Clone the repo:
   ```bash
   git clone https://github.com/ahamza2205/Weather-App.git
