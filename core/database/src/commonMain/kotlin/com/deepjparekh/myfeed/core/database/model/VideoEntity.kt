package com.deepjparekh.myfeed.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val url: String,
)
