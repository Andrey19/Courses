import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "ru.effectivemobile.courses"
    compileSdk = 36

    defaultConfig {
        applicationId = "ru.effectivemobile.courses"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11    }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":course:common-feature"))
    implementation(project(":course:common-interactor"))
    implementation(project(":database"))
    implementation(project(":network"))
    implementation(project(":base-feature"))
    implementation(project(":uikit"))
    implementation(project(":main"))
    implementation(project(":favourites"))
    implementation(project(":entry"))
    implementation(project(":main-screen"))
    implementation(project(":profile"))
    implementation(project(":session:interactor"))
    implementation(project(":transport"))


    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.legacy.support.v4)
    implementation (libs.androidx.fragment.ktx)
    implementation(libs.adapterDelegate4)
    implementation(libs.adapterDelegateViewBinding)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.glide)
    ksp(libs.glide.compiler)

    implementation(libs.hilt.dagger)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}