import Dependencies.applyAppAndroidX
import Dependencies.applyGlide
import Dependencies.applyHilt
import Dependencies.applyOkhttp3
import Dependencies.applyRetrofit2
import Dependencies.applyTest

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = Dependencies.COMPILE_SDK

    defaultConfig {
        applicationId = "com.kjs.kidsnote"
        minSdk = Dependencies.MIN_SDK
        targetSdk = Dependencies.TARGET_SDK
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "picsumUrl", "\"https://picsum.photos/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false

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
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":model"))

    applyAppAndroidX()
    applyTest()
    implementation(Dependencies.Google.MATERIAL)
    applyOkhttp3()
    applyRetrofit2()
    applyGlide()
    applyHilt()
}