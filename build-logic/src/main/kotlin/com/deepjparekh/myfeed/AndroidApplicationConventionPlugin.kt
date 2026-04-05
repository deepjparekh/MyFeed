package com.deepjparekh.myfeed

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("myfeed.static.analysis")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid(this)
                defaultConfig.targetSdk = 36
            }
            configureKotlinMultiplatform()
        }
    }
}
