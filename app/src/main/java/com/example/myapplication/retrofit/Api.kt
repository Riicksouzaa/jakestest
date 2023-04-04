package com.example.myapplication.retrofit

import com.example.myapplication.models.PhotoListModel
import retrofit2.http.GET

interface Api {
    @GET("photos")
    suspend fun getLatestPhotoList() : List<PhotoListModel>
}