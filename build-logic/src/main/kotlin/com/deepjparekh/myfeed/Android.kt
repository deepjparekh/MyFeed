package com.deepjparekh.myfeed

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.JavaVersion

internal fun Project.configureAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = 36 // Use libs.versions.android-compileSdk if possible

        defaultConfig {
            minSdk = 24
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}
