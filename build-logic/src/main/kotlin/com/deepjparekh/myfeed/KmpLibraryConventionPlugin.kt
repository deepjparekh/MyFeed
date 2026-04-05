package com.deepjparekh.myfeed

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
                apply("myfeed.static.analysis")
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
            }
            configureKotlinMultiplatform()
        }
    }
}
