plugins {
    `kotlin-dsl`
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
