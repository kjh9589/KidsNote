buildscript {
    repositories {
        google()
        mavenCentral()
        maven ("https://jitpack.io" )
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Dependencies.Hilt.VERSION}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Dependencies.AndroidX.NAVIGATION_VERSION}")
    }
}