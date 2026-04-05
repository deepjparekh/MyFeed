package com.deepjparekh.myfeed

import org.gradle.api.artifacts.VersionCatalog

internal fun VersionCatalog.versionInt(alias: String): Int {
    val version = findVersion(alias).get().requiredVersion
    return version.toInt()
}
