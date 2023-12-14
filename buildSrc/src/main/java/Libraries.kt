object CustomViews {

    const val Flashbar =
        "com.andrognito.flashbar:flashbar:${Versions.CustomViews.FlashbarVersion}"

}

object ImageLoader {

}

object NetworkLibrary {

    val Retrofit = arrayListOf(
        "com.squareup.retrofit2:retrofit:${Versions.NetworkLibraries.RetrofitVersion}",
        "com.squareup.retrofit2:converter-gson:${Versions.NetworkLibraries.RetrofitVersion}",
        "com.squareup.okhttp3:okhttp:${Versions.NetworkLibraries.OkHttpVersion}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.NetworkLibraries.OkHttpVersion}",
        "com.squareup.retrofit2:converter-scalars:${Versions.NetworkLibraries.RetrofitVersion}",
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.NetworkLibraries.CoroutineAdapterVersion}",
    )

}

object DataBase {

    val Room = arrayListOf(
        "androidx.room:room-runtime:${Versions.DataBase.RoomVersion}",
        "androidx.room:room-compiler:${Versions.DataBase.RoomVersion}",
        "androidx.room:room-ktx:${Versions.DataBase.RoomVersion}"
    )

}

object DI {

    val Koin = arrayListOf(
        "io.insert-koin:koin-core:${Versions.DI.KoinVersion}",
        "io.insert-koin:koin-android:${Versions.DI.KoinAndroidVersion}",
    )


}

object MaterialDesign {

    val Constrainlayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.MaterialDesign.ConstrainLayoutVersion}"

}

object Testing {
    const val Junit = "junit:junit:${Versions.Testing.JunitVersion}"
    const val JunitExtensions =
        "androidx.test.ext:junit:${Versions.Testing.JunitExtensionVersion}"
    const val JunitExtensionsKtx =
        "androidx.test.ext:junit-ktx:${Versions.Testing.androidXTestExtKotlinRunnerVersion}"

    const val Core = "androidx.test:core:${Versions.Testing.TestCoreVersion}"
    const val ArcCore = "androidx.arch.core:core-testing:${Versions.Testing.TestArcCoreVersion}"

    val AndroidX = arrayListOf(
        "androidx.test:core-ktx:${Versions.Testing.androidXTestCoreVersion}",
        "androidx.test.ext:junit-ktx:${Versions.Testing.androidXTestExtKotlinRunnerVersion}",
        "androidx.test:rules:${Versions.Testing.androidXTestRulesVersion}"
    )

    const val Room = "androidx.room:room-testing:${Versions.DataBase.RoomTestingVersion}"


}

object UITesting {
    val Espresso = "androidx.test.espresso:espresso-core:${Versions.UITesting.EspressoVersion}"
    const val ComposeJunit = "androidx.compose.ui:ui-test-junit4"
    const val ComposeToolsJunit = "androidx.compose.ui:ui-tooling"
    const val ComposeManifest = "androidx.compose.ui:ui-test-manifest"
    const val FragmentTest =
        "androidx.fragment:fragment-testing:${Versions.UITesting.FragmentVersion}"
}

object Ktx {

    val Core = "androidx.core:core-ktx:${Versions.Kotlin.CoreKtxVersion}"


}

object Navigation {
   const val ComposeNavigation = "androidx.navigation:navigation-runtime-ktx:${Versions.Jetpack.NavigationComposeVersion}"
}

object Jetpack {

    val Compose = arrayListOf(
        "androidx.activity:activity-compose:${Versions.Jetpack.ActivityComposeVersion}",
        "androidx.compose.ui:ui",
        "androidx.compose.ui:ui-graphics",
        "androidx.compose.ui:ui-tooling-preview",
        "androidx.compose.material3:material3",
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.Jetpack.AccompanistComposeVersion}"
    )

    val ComposePlatform = "androidx.compose:compose-bom:${Versions.Jetpack.ComposeVersion}"

    val LifeCycle = arrayListOf(

        //for viewModelScope
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Jetpack.LifeCycleVersion}",
        //for lifecycleScope
        // Common
    )

}

object JsonParser {

    val Moshi = arrayListOf(
        "com.squareup.moshi:moshi-kotlin-codegen:${Versions.JsonParser.MoshiVersion}",
        "com.squareup.moshi:moshi-kotlin:${Versions.JsonParser.MoshiVersion}"
    )

    const val Gson = "com.google.code.gson:gson:${Versions.JsonParser.GoogleVersion}"

}
