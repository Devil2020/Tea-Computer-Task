import com.android.sdklib.repository.meta.DetailsTypes.AddonDetailsType.Libraries
import org.gradle.kotlin.dsl.kapt

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
        getByName( "release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug"){
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "api", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "key", "\"12ae0210d107863fd1d89b1e2ee1f26a\"")
            buildConfigField("String", "accessToken", "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMmFlMDIxMGQxMDc4NjNmZDFkODliMWUyZWUxZjI2YSIsInN1YiI6IjViMjQ0OGViMGUwYTI2NjcyOTAwMDMzZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.X8Q22LOwOYKWYrpKoiE6ie6IoYUzOPqyJHAEsP0tvfI\"")
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
        buildConfig = true
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
    implementation(ImageLoader.Coin)
    NetworkLibrary.Retrofit.forEach { library ->
        implementation(library)
    }
    Observable.RX.forEach {library ->
        implementation(library)
    }
    implementation(JsonParser.Gson)
    testImplementation(Testing.Junit5)
    DataBase.Room.forEach { library ->
        implementation(library)
    }


    kapt(DataBase.RoomKapt)
    DI.Koin.forEach { library ->
        implementation(library)
    }
    implementation(MaterialDesign.Constrainlayout)
    implementation(Navigation.ComposeNavigation)
    implementation(Navigation.NavigationRuntime)
    testImplementation(Testing.Junit)
    androidTestImplementation(Testing.JunitExtensions)
    androidTestImplementation(UITesting.Espresso)
    testImplementation(UITesting.Truth)
    Observable.RX.forEach {library ->
        androidTestImplementation(library)
    }
    androidTestImplementation(platform(Jetpack.ComposePlatform))
    androidTestImplementation(UITesting.ComposeJunit)
    androidTestImplementation(UITesting.Core)
    debugImplementation(UITesting.ComposeToolsJunit)
    debugImplementation(UITesting.ComposeManifest)
}