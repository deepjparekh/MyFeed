package com.deepjparekh.myfeed.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.deepjparekh.myfeed.core.database.model.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Insert
    suspend fun insert(video: VideoEntity)

    @Delete
    suspend fun delete(video: VideoEntity)

    @Query("SELECT * FROM VideoEntity")
    fun getAllAsFlow(): Flow<List<VideoEntity>>
}
