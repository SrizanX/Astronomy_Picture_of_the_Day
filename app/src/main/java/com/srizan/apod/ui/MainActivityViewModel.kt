package com.srizan.apod.ui


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srizan.apod.api.ApodService
import com.srizan.apod.model.Picture
import com.srizan.apod.repository.PictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    private val apodService: ApodService,
    private val pictureRepository: PictureRepository
) : ViewModel() {

    val picture = pictureRepository.picture
    val isLoading = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            isLoading.postValue(true)
            pictureRepository.refreshPicture()
            isLoading.postValue(false)
        }
    }


}