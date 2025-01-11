package com.chinky.localandroidapplication.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chinky.localandroidapplication.repository.ApiService
import com.chinky.localandroidapplication.activity.family.viewModel.FamilyActivityViewModel

class ViewModelFactory(private val service: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FamilyActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return FamilyActivityViewModel(service) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}