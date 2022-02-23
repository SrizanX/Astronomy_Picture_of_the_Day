package com.srizan.apod

import android.app.Application
import android.os.Build
import androidx.work.*
import com.srizan.apod.worker.RefreshImageWorker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class App: Application(){
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayInit()
    }

    private fun setupWorker(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    setRequiresDeviceIdle(true)
                }
            }.build()
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshImageWorker>(1,TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshImageWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }

    private fun delayInit(){
        applicationScope.launch {
            setupWorker()
        }
    }


}