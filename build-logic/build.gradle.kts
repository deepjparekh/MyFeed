plugins {
    `kotlin-dsl`
}

group = "com.deepjparekh.myfeed.buildlogic"

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
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
    }
}
