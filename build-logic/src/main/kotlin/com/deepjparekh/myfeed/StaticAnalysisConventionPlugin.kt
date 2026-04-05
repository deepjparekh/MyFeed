package com.deepjparekh.myfeed

import com.diffplug.gradle.spotless.SpotlessExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType

class StaticAnalysisConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt")
                    ktlint().editorConfigOverride(mapOf(
                        "ktlint_standard_function-naming" to "disabled",
                        "ktlint_standard_no-wildcard-imports" to "disabled"
                    ))
                }
                kotlinGradle {
                    target("**/*.gradle.kts")
                    targetExclude("**/build/**/*.gradle.kts")
                    ktlint().editorConfigOverride(mapOf(
                        "ktlint_standard_function-naming" to "disabled",
                        "ktlint_standard_no-wildcard-imports" to "disabled"
                    ))
                }
            }

            extensions.configure<DetektExtension> {
                toolVersion = libs.findVersion("detekt").get().toString()
                config.setFrom(rootProject.file("config/detekt/detekt.yml"))
                buildUponDefaultConfig = true
                allRules = false
            }

            tasks.withType<Detekt>().configureEach {
                exclude { it.file.absolutePath.contains("/build/") }
                reports {
                    html.required.set(true)
                    xml.required.set(true)
                    txt.required.set(true)
                }
            }
        }
    }
}
