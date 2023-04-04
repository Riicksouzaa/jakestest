package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Event
import com.example.myapplication.models.PhotoListModel
import com.example.myapplication.retrofit.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() :
    ViewModel() {

    // LIVE DATA
    private val _photos = MutableLiveData<List<PhotoListModel>>()
    val photos: LiveData<List<PhotoListModel>> = _photos

    // BUTTON EVENTS
    val primaryButtonEventLiveData: LiveData<Event<Boolean>> get() = _primaryButtonEventLiveData
    private val _primaryButtonEventLiveData = MutableLiveData<Event<Boolean>>()

    // LOGIN EVENTS
    val loginSuccess = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        loginSuccess.value = email.isNotEmpty() && password.isNotEmpty()
    }

    private val apiService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    init {
        viewModelScope.launch {
            _photos.value = apiService.getLatestPhotoList()
        }
    }
}