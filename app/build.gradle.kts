import org.gradle.kotlin.dsl.sourceSets
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "com.example.medchain"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.medchain"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val localProps = Properties().apply {
            val file = rootProject.file("local.properties")
            if (file.exists()) {
                file.inputStream().use { load(it) }
            }
        }
        val baseUrlApi = localProps.getProperty("BASE_URL_API", "https://default.url/")
        buildConfigField("String", "BASE_URL_API", "\"$baseUrlApi\"")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    ksp(libs.bundles.dagger.ksp)
    //hilt
    implementation(libs.hilt.dagger)
    //navigation
    implementation(libs.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    //coil
    implementation(libs.coil)
    //room
    implementation(libs.bundles.room.impl)
    ksp(libs.room.compiler)
    //retrofit
    implementation(libs.bundles.retrofit)
    //coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    //work manager
    implementation(libs.work.runtime.ktx)
    implementation(libs.compose.ui)

    implementation(libs.serialization.json)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.core.splashscreen)
    implementation (libs.androidx.compose.material.icons.extended)

    //androidX
    implementation(libs.camera.mlkit.vision)
    implementation(libs.mlkit.barcode.scanning)

    implementation(libs.androidx.camera.core)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.accompanistPermissions)
}