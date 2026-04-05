plugins {
    `kotlin-dsl`
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

group = "com.deepjparekh.myfeed.buildlogic"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.room.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.spotless.gradlePlugin)
}

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**/*.kt")
        ktlint().editorConfigOverride(
            mapOf("ktlint_standard_function-naming" to "disabled"),
        )
    }
    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude("**/build/**/*.gradle.kts")
        ktlint()
    }
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(file("../config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    allRules = false
    parallel = true
    basePath = rootDir.parentFile.absolutePath
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    exclude { it.file.absolutePath.contains("/build/") }
    reports {
        html.required.set(true)
        xml.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}

tasks.register("qualityCheck") {
    group = "verification"
    description = "Runs formatting and static analysis checks for build logic."
    dependsOn("spotlessCheck", "detekt")
}

tasks.register("qualityFix") {
    group = "formatting"
    description = "Applies automatic formatting fixes for build logic."
    dependsOn("spotlessApply")
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = "myfeed.kmp.library"
            implementationClass = "com.deepjparekh.myfeed.KmpLibraryConventionPlugin"
        }
        register("kmpLibraryCompose") {
            id = "myfeed.kmp.library.compose"
            implementationClass = "com.deepjparekh.myfeed.KmpLibraryComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "myfeed.android.application"
            implementationClass = "com.deepjparekh.myfeed.AndroidApplicationConventionPlugin"
        }
        register("composeMultiplatform") {
            id = "myfeed.compose.multiplatform"
            implementationClass = "com.deepjparekh.myfeed.ComposeMultiplatformConventionPlugin"
        }
        register("staticAnalysis") {
            id = "myfeed.static.analysis"
            implementationClass = "com.deepjparekh.myfeed.StaticAnalysisConventionPlugin"
        }
    }
}
