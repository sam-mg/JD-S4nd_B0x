plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.jd_s4nd_b0x.sc4nvu3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jd_s4nd_b0x.sc4nvu3"
        minSdk = 26
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
}

dependencies {
//    implementation("com.google.android.gms:play-services-vision:20.1.3")
    implementation(libs.play.services.vision)
//    implementation("com.google.zxing:core:3.4.1")
    implementation(libs.zxing.core)
    implementation(libs.zxing.android.embedded)
    implementation ("androidx.camera:camera-camera2:1.2.1")
    implementation ("androidx.camera:camera-lifecycle:1.2.1")
    implementation ("androidx.camera:camera-view:1.2.1")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}