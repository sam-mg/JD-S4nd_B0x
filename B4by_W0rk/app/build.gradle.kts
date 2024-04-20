plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.jd_s4nd_b0x.b4by_w0rk"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jd_s4nd_b0x.b4by_w0rk"
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
//    Dependencies For Retrofit
//    This dependency facilitates sending and receiving GET and POST requests from APIs over the internet.
//    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation(libs.retrofit)
//    This dependency is the Gson Converter, developed by Google to convert JSON responses into Java objects.
//    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation(libs.converter.gson)
//    This is the Logging Interceptor dependency, used to log all HTTP requests sent and received.
//    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation(libs.logging.interceptor)

//    Dependencies for Room Database
//    Dependency for working with SQL DataBase
    implementation(libs.room.runtime)
//    Dependency for work with database-related code during compilation
    annotationProcessor(libs.room.compiler)

//    A Powerful library for Loading and Displaying images
//    implementation("com.squareup.picasso:picasso:2.71828")
    implementation(libs.picasso)

//    Dependency for Accessing Resources
//    This is a dependency for AndroidX Core library providing utility classes for resource management and permission handling.
//    implementation("androidx.core:core:1.13.0")
    implementation(libs.core)

//    Dependency for FireBase Authentication
//    This is a dependency used for authenticating individuals; new users can register, while already registered users can log in.
    implementation(libs.firebase.auth)

//    Default Implementations
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}