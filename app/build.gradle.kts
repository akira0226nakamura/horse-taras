plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.horsetaras"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.horsetaras"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    val composeBom = platform("androidx.compose:compose-bom:2025.01.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.coil.compose)
    implementation(libs.lottie.compose)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    // Material Design 3 for UI Components
    implementation(libs.material3)

    // Core UI dependencies
    implementation(libs.ui)
    implementation(libs.androidx.foundation)

    // Preview & Debugging
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    // Navigation for Jetpack Compose
    implementation(libs.androidx.navigation.compose)

    // Accompanist for Animated Navigation (Optional, but recommended)
    implementation(libs.accompanist.navigation.animation)

    // Optional - ViewModel integration (recommended for state management)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Optional - LiveData integration (if needed)
    implementation(libs.androidx.runtime.livedata)

    // Optional - Material Icons (if using built-in icons)
    implementation(libs.androidx.material.icons.extended)

    // Activity & Lifecycle (for Compose integration)
    implementation(libs.androidx.activity.compose)

    // UI Testing
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)
}
