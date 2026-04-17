package com.deepjparekh.myfeed.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.deepjparekh.myfeed.core.database.dao.SettingsDao
import com.deepjparekh.myfeed.core.database.dao.VideoDao
import com.deepjparekh.myfeed.core.database.model.SettingsEntity
import com.deepjparekh.myfeed.core.database.model.VideoEntity

@Database(entities = [VideoEntity::class, SettingsEntity::class], version = 1)
@ConstructedBy(MyFeedDatabaseConstructor::class)
abstract class MyFeedDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun settingsDao(): SettingsDao
}

@Suppress("KotlinNoActualForExpect", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object MyFeedDatabaseConstructor : RoomDatabaseConstructor<MyFeedDatabase> {
    override fun initialize(): MyFeedDatabase
}
