@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        dependencies {
            debugImplementation(libs.androidx.ui.test.manifest)
        }

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant {
            sourceSetTree.set(KotlinSourceSetTree.test)
        }

        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        androidInstrumentedTest.dependencies {
            implementation("androidx.test:core:1.5.0")
            // for `AndroidJUnit4`
            implementation("androidx.test.ext:junit:1.1.5")
            // your coroutine runtime
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
            implementation(libs.junit)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            implementation(libs.kotlin.test)
            implementation(compose.uiTest)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        commonTest.dependencies {
            implementation(libs.junit)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            implementation(libs.kotlin.test)
            implementation(compose.uiTest)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.client.android)
            implementation(compose.preview)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        iosTest.dependencies {
            implementation(libs.junit)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            implementation(libs.kotlin.test)
            implementation(compose.uiTest)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.navigation.compose)
        }
    }
}

android {
    namespace = "org.example.shared"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
