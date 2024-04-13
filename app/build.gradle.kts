plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.androidxSafeArgs)
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-parcelize")

}

android {
    namespace = "com.gerhard.cs50x"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gerhard.cs50x"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "TWITTER_BASE_URL", "${project.property("TWITTER_BASE_URL")}")
            buildConfigField("String", "RAPID_API_KEY", "${project.property("RAPID_API_KEY")}")
            buildConfigField("String", "RAPID_API_HOST", "${project.property("RAPID_API_HOST")}")
        }

        getByName("debug") {
            isDefault = true
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "TWITTER_BASE_URL", "${project.property("TWITTER_BASE_URL")}")
            buildConfigField("String", "RAPID_API_KEY", "${project.property("RAPID_API_KEY")}")
            buildConfigField("String", "RAPID_API_HOST", "${project.property("RAPID_API_HOST")}")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    // Note: lifecycle-extensions is deprecated and no longer needed with newer versions
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.common.java8)

    // Image
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // Compose
    implementation(libs.activity.compose)
    implementation(libs.compose.ui)
    debugImplementation(libs.compose.ui.tool.preview)
    implementation(libs.compose.material)
    debugImplementation(libs.compose.ui.tooling)
//        debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.material3)

    // AndroidX Security Crypto - Encrypted Shared Preferences
    implementation(libs.security.crypto)

    // Androidx
    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.preference.ktx)

    // Serialization
    implementation(libs.gson)
    implementation(libs.moshi.kotlin)
    implementation(libs.kotlinx.serialization.json)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.test)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    // Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.dynamic.features.fragment)

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.core.testing)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)

    androidTestImplementation(libs.test.rules)


}