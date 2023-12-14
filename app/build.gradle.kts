import com.android.sdklib.repository.meta.DetailsTypes.AddonDetailsType.Libraries

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "sa.com.morse.teacomputertask"
    compileSdk = 34

    defaultConfig {
        applicationId = "sa.com.morse.teacomputertask"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = arrayListOf("-Xcontext-receivers")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Ktx.Core)
    Jetpack.LifeCycle.forEach { library ->
        implementation(library)
    }
    implementation(platform(Jetpack.ComposePlatform))
    Jetpack.Compose.forEach { library ->
        implementation(library)
    }
    implementation (MaterialDesign.Constrainlayout)
    implementation(Navigation.ComposeNavigation)
    implementation("androidx.navigation:navigation-compose:2.7.5")
    testImplementation(Testing.Junit)
    androidTestImplementation( Testing.JunitExtensions)
    androidTestImplementation(UITesting.Espresso)
    androidTestImplementation(platform(Jetpack.ComposePlatform))
    androidTestImplementation(UITesting.ComposeJunit)
    debugImplementation(UITesting.ComposeToolsJunit)
    debugImplementation(UITesting.ComposeManifest)
}