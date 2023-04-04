package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

data class PhotoListModel(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("thumbnailUrl") val thumbnail: String? = null,
)
