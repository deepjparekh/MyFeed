plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

tasks.register("qualityCheck") {
    group = "verification"
    description = "Runs formatting and static analysis checks for all modules."
    dependsOn(gradle.includedBuild("build-logic").task(":qualityCheck"))
}

tasks.register("qualityFix") {
    group = "formatting"
    description = "Applies automatic formatting fixes for all modules."
    dependsOn(gradle.includedBuild("build-logic").task(":qualityFix"))
}

gradle.projectsEvaluated {
    val staticAnalysisProjects = allprojects.filter { project ->
        project.pluginManager.hasPlugin("myfeed.static.analysis")
    }

    tasks.named("qualityCheck").configure {
        dependsOn(
            staticAnalysisProjects.flatMap { project ->
                listOf(
                    "${project.path}:spotlessCheck",
                    "${project.path}:detekt",
                )
            },
        )
    }

    tasks.named("qualityFix").configure {
        dependsOn(
            staticAnalysisProjects.map { project -> "${project.path}:spotlessApply" },
        )
    }
}
