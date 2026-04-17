package com.deepjparekh.myfeed.core.database.model

sealed interface AutoScrollMode {
    data object Disabled : AutoScrollMode
    data class Timed(val intervalSeconds: Int) : AutoScrollMode
}
