package com.srizan.apod.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srizan.apod.repository.PictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
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