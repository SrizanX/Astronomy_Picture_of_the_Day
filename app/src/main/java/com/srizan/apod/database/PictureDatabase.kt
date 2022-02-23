package com.srizan.apod.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.srizan.apod.model.Picture

@Database(entities = [Picture::class], version = 1)
abstract class PictureDatabase : RoomDatabase() {
    abstract val pictureDao : PictureDao
}