package com.srizan.apod.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.srizan.apod.model.Picture

@Dao
interface PictureDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(picture: Picture)

    @Query("Select * FROM Picture")
    fun getPicture() : LiveData<Picture>
}