package com.srizan.apod.api

import com.srizan.apod.model.Picture
import retrofit2.http.GET

interface ApodService {
    @GET("/planetary/apod?api_key=IQyFAPfa7fKeZz8TziFa2qSV8vHOTONWw4M0QBRC")
    suspend fun getPicture() : Picture
}
