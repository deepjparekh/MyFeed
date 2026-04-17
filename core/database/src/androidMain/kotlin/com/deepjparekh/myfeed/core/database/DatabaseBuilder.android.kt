package com.deepjparekh.myfeed.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MyFeedDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("myfeed.db")
    return Room.databaseBuilder<MyFeedDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}
