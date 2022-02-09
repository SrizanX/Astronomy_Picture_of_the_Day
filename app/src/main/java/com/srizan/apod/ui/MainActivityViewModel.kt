package com.srizan.apod.ui


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srizan.apod.api.ApodService
import com.srizan.apod.model.Picture
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    private val apodService : ApodService)
    : ViewModel() {

    val pictureLiveData = MutableLiveData<Picture>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        //getPicture()
    }

    fun getPicture(){
        viewModelScope.launch {
            withTimeout(5000){
                try {
                    isLoading.value = true
                    pictureLiveData.value = apodService.getPicture()
                    isLoading.value = false
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}