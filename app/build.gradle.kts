plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") // KAPT para Room
}

android {
    namespace = "com.example.marketlist"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.marketlist"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val apiKey = project.findProperty("OPENAI_API_KEY") as String? ?: ""

        buildConfigField(
            "String",
            "OPENAI_API_KEY",
            "\"$apiKey\""
        )
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
    kotlinOptions {
        jvmTarget = "11"
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
    implementation("androidx.compose.material:material-icons-extended") //Ícones
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0") //Viewmodel
    implementation("androidx.compose.runtime:runtime-livedata:1.10.1") //Data em tempo real
    implementation("androidx.navigation:navigation-compose:2.7.7") //Navegação
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.1")
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.foundation) //Google Fontes
    implementation("com.squareup.retrofit2:retrofit:2.9.0") //Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") //Converter para Retrofit
    implementation("com.google.code.gson:gson:2.10.1") //Gson para Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") //Interceptor para Retrofit
    implementation(libs.androidx.compose.foundation.layout)
    val room_version = "2.8.4" //Room
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version") //KAPT para Room
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}