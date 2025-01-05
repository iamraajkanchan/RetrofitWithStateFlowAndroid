package com.chinky.localandroidapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chinky.localandroidapplication.repository.ApiService

class ViewModelFactory(private val service: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainActivityViewModel(service) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}