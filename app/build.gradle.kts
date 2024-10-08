plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    //id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.apcoding.helloyaar"
    compileSdk = 34

        packagingOptions {
            exclude ("META-INF/DEPENDENCIES")
        }



    defaultConfig {
        applicationId = "com.apcoding.helloyaar"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    allprojects {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-auth-ktx:21.1.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.6.0")
    implementation("com.google.firebase:firebase-storage-ktx:20.2.0")
    implementation("com.google.firebase:firebase-messaging-ktx:23.1.2")
    //implementation("com.google.firebase:firebase-crashlytics-ktx:18.3.7")
    implementation("com.google.firebase:firebase-analytics-ktx:21.3.0")
    implementation("com.google.firebase:firebase-appdistribution-gradle:5.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // MVVM
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0")

    // Coil
    implementation("io.coil-kt:coil-compose:1.4.0")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Swipe Refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.28.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // Zooming
    implementation("me.saket.telephoto:zoomable:0.4.0")

    // SwipeAction
    implementation("me.saket.swipe:swipe:1.2.0")

    // ExoPlayer
    implementation("com.google.android.exoplayer:exoplayer:2.18.0")

    // Room
    implementation("androidx.room:room-ktx:2.2.1")
    kapt("androidx.room:room-compiler:2.2.1")

    // Preferences datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Image cropper
    implementation("com.vanniktech:android-image-cropper:4.5.0")

    // Animated Navigation
    implementation("com.google.accompanist:accompanist-navigation-animation:0.24.13-rc")

}