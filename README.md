# 🎬 Movie & TV Shows App
A modern Android app built with Jetpack Compose that allows users to browse and search for movies and TV shows. The app provides detailed information on each title and supports navigation between screens.

## 🚀 Tech Stack
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit for Android
- **Material 3** - UI components and theming
- **Koin** - Dependency Injection
- **Navigation Component** - For screen navigation
- **Coil** - Image loading
- **Retrofit** - For API calls
- **Coroutines & Flow** - For asynchronous operations

## 📜 Overview
This app allows users to browse Movies & TV Shows using an intuitive UI. The home screen features a toggle button to switch between movies and TV shows, a search bar to filter results, and a grid layout for displaying content. Users can click on a movie or show to view its details.

## 🌟 Features
- ✅ Browse & Search for movies and TV shows
- ✅ Toggle between Movies and TV Shows
- ✅ View details of a selected movie/show
- ✅ Smooth animations and Material 3 styling
- ✅ Shimmer effect for a better loading experience
- ✅ Optimized UI with LazyVerticalGrid
- ✅ Navigation support with Jetpack Compose
- ✅ Dependency injection using Koin

## 🔥 How It Works
1. Home Screen:

- Users can switch between Movies and TV Shows using the toggle buttons.
- A search bar allows users to filter results dynamically.
- Data is fetched asynchronously and displayed in a grid format.
- Clicking on a movie/show navigates to the Details Screen.

2. Details Screen:

- Displays movie/show poster, title, and other details.
- API call retrieves additional information when required.

3. Data Fetching:

- Uses Retrofit and Coroutines to fetch data.
- The app stores API responses efficiently to ensure a smooth experience.
