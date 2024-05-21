plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.9.24"
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "lazy.initialization.examples"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "lazy.initialization.examples"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {

    implementation(lib.appCompat)
    implementation(lib.androidxDatastore)
    implementation(lib.constraintLayout)
    implementation(lib.coreKtx)

    implementation(lib.dagger)
    kapt(lib.daggerCompiler)

    implementation(lib.kotlinxCoroutinesAndroid)
    implementation(lib.kotlinxCoroutinesCore)
    implementation(lib.kotlinxSerializationJson)
    implementation(lib.kotlinReflect)
    implementation(lib.material)
    implementation(lib.retrofit)
    implementation(lib.retrofitAdapterRxJava)
    implementation(lib.retrofitConverterGson)
    implementation(lib.retrofitKotlinSerialization)
    implementation(lib.okHttp)
    implementation(lib.okHttpLoggingInterceptor)

    implementation(lib.roomRuntime)
    implementation(lib.roomKtx)
    kapt(lib.roomCompiler)

    testImplementation(testLib.junit)
    testImplementation(testLib.junitAndroidRunner)
}