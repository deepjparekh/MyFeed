package com.deepjparekh.myfeed

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class KmpLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("myfeed.kmp.library")
                apply("myfeed.compose.multiplatform")
            }
        }
    }
}
