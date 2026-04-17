package com.deepjparekh.myfeed.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val DEFAULT_TIMER_INTERVAL_SECONDS = 15

@Entity
data class SettingsEntity(
    @PrimaryKey val id: Int = 0,
    val isMuted: Boolean = false,
    val autoScrollEnabled: Boolean = true,
    val timerIntervalSeconds: Int = DEFAULT_TIMER_INTERVAL_SECONDS,
) {
    val autoScrollMode: AutoScrollMode
        get() = if (autoScrollEnabled) {
            AutoScrollMode.Timed(timerIntervalSeconds)
        } else {
            AutoScrollMode.Disabled
        }
}
