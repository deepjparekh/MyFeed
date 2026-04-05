plugins {
    alias(libs.plugins.myfeed.kmp.library.compose)
}

android {
    namespace = "com.deepjparekh.myfeed.core.designsystem"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.material3)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
        }
    }
}
