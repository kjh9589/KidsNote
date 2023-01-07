import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.exclude

object Dependencies {
    const val COMPILE_SDK = 31
    const val MIN_SDK = 24
    const val TARGET_SDK = 31

    private const val implementation = "implementation"
    private const val testImplementation = "testImplementation"
    private const val androidTestImplementation = "androidTestImplementation"

    private const val kapt = "kapt"
    private const val kaptTest = "kaptTest"
    private const val kaptAndroidTest = "kaptAndroidTest"

    object Kotlin {
        const val VERSION = "1.6.0"
        const val STD_LIB = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
        const val TEST = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
    }

    fun DependencyHandlerScope.applyKotlin() {
        implementation(Kotlin.STD_LIB)
        testImplementation(Kotlin.TEST)
    }

    object Coroutine {
        const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Kotlin.VERSION}"
        const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1"
        const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.VERSION}"
    }

    fun DependencyHandlerScope.applyCoroutine() {
        implementation(Coroutine.COROUTINE)
        implementation(Coroutine.COROUTINE_CORE)
        testImplementation(Coroutine.COROUTINE_TEST)
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:1.6.1"
        const val GSON = "com.google.code.gson:gson:2.9.0"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.8.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.4.2"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"

        // Paging3
        const val PAGING3_VERSION = "3.1.1"

        const val PAGING3_RUNTIME = "androidx.paging:paging-runtime:$PAGING3_VERSION"
        const val PAGING3_COMMON = "androidx.paging:paging-common:$PAGING3_VERSION"

        // Navigation
        const val NAVIGATION_VERSION = "2.5.0"

        const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
        const val UI = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"

        // LegacySupport
        const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:1.0.0"

        // LifeCycle
        const val LIFE_CYCLE_VERSION = "2.5.0"

        const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:$LIFE_CYCLE_VERSION"
        const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFE_CYCLE_VERSION"

        // Room
        const val ROOM_VERSION = "2.4.3"
        const val ROOM = "androidx.room:room-ktx:$ROOM_VERSION"
        const val ROOM_COMPILER = "androidx.room:room-compiler:$ROOM_VERSION"
    }

    fun DependencyHandlerScope.applyAppAndroidX() {
        implementation(AndroidX.CORE)
        implementation(AndroidX.APPCOMPAT)
        implementation(AndroidX.CONSTRAINT_LAYOUT)
        implementation(AndroidX.PAGING3_RUNTIME)
        implementation(AndroidX.PAGING3_COMMON)
        implementation(AndroidX.FRAGMENT)
        implementation(AndroidX.UI)
        implementation(AndroidX.LEGACY_SUPPORT)
        implementation(AndroidX.LIVEDATA)
        implementation(AndroidX.VIEWMODEL)
        implementation(AndroidX.ROOM)
        kapt(AndroidX.ROOM_COMPILER)
    }

    fun DependencyHandlerScope.applyDataAndroidX() {
        implementation(AndroidX.PAGING3_RUNTIME)
        implementation(AndroidX.PAGING3_COMMON)
        implementation(AndroidX.ROOM)
        kapt(AndroidX.ROOM_COMPILER)
    }

    fun DependencyHandlerScope.applyDomainAndroidX() {
        implementation(AndroidX.PAGING3_RUNTIME)
        implementation(AndroidX.PAGING3_COMMON)
    }

    object Okhttp3 {
        const val VERSION = "4.10.0"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:$VERSION"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$VERSION"
    }

    fun DependencyHandlerScope.applyOkhttp3() {
        implementation(Okhttp3.OKHTTP)
        implementation(Okhttp3.LOGGING_INTERCEPTOR)
    }

    object Retrofit2 {
        const val VERSION = "2.9.0"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:$VERSION"
        const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:$VERSION"
    }

    fun DependencyHandlerScope.applyRetrofit2() {
        implementation(Retrofit2.RETROFIT)
        implementation(Retrofit2.GSON_CONVERTER)
        implementation(Google.GSON)
    }

    object Glide {
        const val VERSION = "4.13.2"
        const val CORE = "com.github.bumptech.glide:glide:$VERSION"
        const val COMPILER = "com.github.bumptech.glide:compiler:$VERSION"
        const val OKHTTP3_INTEGRATION = "com.github.bumptech.glide:okhttp3-integration:$VERSION"
        const val TRANSFORMATIONS = "jp.wasabeef:glide-transformations:4.3.0"
    }

    fun DependencyHandlerScope.applyGlide() {
        implementation(Glide.CORE)
        kapt(Glide.COMPILER)
        implementation(Glide.OKHTTP3_INTEGRATION)
        implementation(Glide.TRANSFORMATIONS)
    }

    object Test {
        const val JUNIT = "junit:junit:4.13.2"
        const val ANDROID_EXT_JUNIT = "androidx.test.ext:junit:1.1.3"
        const val ANDROID_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
    }

    fun DependencyHandlerScope.applyTest() {
        testImplementation(Test.JUNIT)
        androidTestImplementation(Test.ANDROID_EXT_JUNIT)
        androidTestImplementation(Test.ANDROID_ESPRESSO_CORE)
    }

    object Hilt {
        const val VERSION = "2.38.1"

        const val CORE = "com.google.dagger:hilt-android:$VERSION"
        const val COMPILER = "com.google.dagger:hilt-compiler:$VERSION"

        // For instrumentation tests
        const val ANDROID_TESTING = "com.google.dagger:hilt-android-testing:$VERSION"
        const val ANDROID_TESTING_COMPILER = "com.google.dagger:hilt-compiler:$VERSION"

        // For local unit tests
        const val LOCAL_TESTING = "com.google.dagger:hilt-android-testing:$VERSION"
        const val LOCAL_TESTING_COMPILER = "com.google.dagger:hilt-compiler:$VERSION"
    }

    fun DependencyHandlerScope.applyHilt(){
        implementation(Hilt.CORE)
        kapt(Hilt.COMPILER)

        kaptTest(Hilt.LOCAL_TESTING_COMPILER)
        testImplementation(Hilt.LOCAL_TESTING)

        androidTestImplementation(Hilt.ANDROID_TESTING)
        kaptAndroidTest(Hilt.ANDROID_TESTING_COMPILER)
    }

}
