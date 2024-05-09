plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.hrhn"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.hrhn"
        minSdk = 26
        targetSdk = 33
        versionCode = 3
        versionName = "1.0.1"
        setProperty("archivesBaseName", "HRHN-v${versionName}(${versionCode})")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    bundle {
        storeArchive {
            enable = false
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
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

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.5.5")
    implementation("androidx.preference:preference:1.2.0")
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    val hilt_version = "2.44"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    val room_version = "2.5.0"
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-paging:$room_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}