plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.google.wishcraft"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.google.wishcraft"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.appVersionCode
        versionName = AppConfig.appVersionName

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

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {


    addUiDependencies()
    addCoroutinesDependencies()
    addAndroidTestsDependencies()
    addExifInterface()
    addCameraDependencies()
    addCameraDependencies()
    addNetworkDependencies()
    addKoinDependencies()
    addAndroidLifecycleDependencies()
    addTimberDependency()
}