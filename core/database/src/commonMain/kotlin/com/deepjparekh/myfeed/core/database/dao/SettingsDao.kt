package com.deepjparekh.myfeed.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.deepjparekh.myfeed.core.database.model.SettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Upsert
    suspend fun upsert(settings: SettingsEntity)

    @Query("SELECT * FROM SettingsEntity WHERE id = 0")
    fun getSettingsAsFlow(): Flow<SettingsEntity?>
}
