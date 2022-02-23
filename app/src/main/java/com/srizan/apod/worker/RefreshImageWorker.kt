package com.srizan.apod.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.srizan.apod.repository.PictureRepository
import javax.inject.Inject

class RefreshImageWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshImageWorker"
    }

    @Inject
    lateinit var pictureRepository: PictureRepository
    override suspend fun doWork(): Result {
        return try {
            pictureRepository.refreshPicture()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}