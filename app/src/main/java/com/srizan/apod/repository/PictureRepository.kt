package com.srizan.apod.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.srizan.apod.api.ApodService
import com.srizan.apod.database.PictureDao
import com.srizan.apod.model.Picture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class PictureRepository
@Inject constructor(
    val pictureDao: PictureDao,
    val apodService: ApodService
) {
    val picture: LiveData<Picture> = pictureDao.getPicture()


    suspend fun refreshPicture() {
        withContext(Dispatchers.IO) {
            try {
                val newPicture = apodService.getPicture()
                pictureDao.insert(newPicture)
                Log.d("ASD", "Picture Received: $newPicture")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("ASD", "Picture in not Received: ${e.localizedMessage}")
            }
        }
    }

}