object Versions {
    val compileSdk = 29
    val buildTools = "29.0.2"
    val minSdk = 21
    val targetSdk = 29
    val versionCode = 1
    val versionName = "1.0"

    val gradleTools = "3.6.1"
    val kotlin = "1.4.0"
    val appCompat = "1.1.0"
    val coreKtx = "1.2.0"
    val material = "1.2.0-alpha05"
    val constraintLayout = "1.1.3"
    val navigation = "2.3.0-alpha04"
    val lifecycle = "2.2.0"
    val easyValidation = "1.0.1"
    val coroutines = "1.3.3"
    val room = "2.2.4"
    val okHttp = "4.3.1"
    val retrofit = "2.7.1"
    val retrofitCoroutines = "0.9.2"
    val retrofitMoshi = "2.7.1"
    val dagger = "2.27"
    val coil = "0.9.5"

    // Test
    val junit = "4.13"
    val extJunitTruth = "1.1.0"
    val espresso = "3.2.0"
    val androidxTest = "1.1.0"
    val archCoreTest = "2.1.0"
    val androidTestRunnerRules = "1.2.0"
    val robolectric = "4.3.1"
    val mockito = "3.2.4"
    val fragment = "1.2.3"
    val dataBinding = "4.1.0-alpha04"
    val circleImageView = "3.1.0"
    val timber = "4.7.1"

}

object Modules {
    val core = ":core"
    val auth = ":auth"
    val appointments = ":appointments"
    val medicalfile = ":medicalfile"
    val profile = ":profile"
}

object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val lifecycleViewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    // Coroutines
    val kotlinCoroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // ROOM
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // OKHTTP
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // RETROFIT
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}"
    val retrofitCoroutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    val circleImageView = "de.hdodenhof:circleimageview:${Versions.circleImageView}"

    val coil = "io.coil-kt:coil:${Versions.coil}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

}


object TestDependencies {

    // ANDROID TEST
    val junit = "junit:junit:${Versions.junit}"

    // Core library
    val androidTestCoreKtx = "androidx.test:core-ktx:${Versions.androidxTest}"

    // AndroidJUnitRunner and JUnit Rules
    val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunnerRules}"
    val androidTestRules = "androidx.test:rules:${Versions.androidTestRunnerRules}"

    // Assertions
    val extJunitKtx = "androidx.test.ext:junit-ktx:${Versions.extJunitTruth}"
    val extTruth = "androidx.test.ext:truth:${Versions.extJunitTruth}"
//    val googleTruth = "com.google.truth:truth:${Versions.googleTruth}"

    val dataBindingCompiler = "androidx.databinding:databinding-compiler:${Versions.dataBinding}"

    // Test helpers for LiveData
    val archCore = "androidx.arch.core:core-testing:${Versions.archCoreTest}"

    val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragment}"

    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    // Coroutines
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    // Room DB
    val room = "androidx.room:room-testing:${Versions.room}"

    // Robolectric
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    // Mockito
    val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"

}