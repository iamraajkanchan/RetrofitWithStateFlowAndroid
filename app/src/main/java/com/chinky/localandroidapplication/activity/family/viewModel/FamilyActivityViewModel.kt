package com.chinky.localandroidapplication.activity.family.viewModel

import androidx.lifecycle.ViewModel
import com.chinky.localandroidapplication.repository.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FamilyActivityViewModel(private val apiService: ApiService) : ViewModel() {

    private val mutableFamilyListFlow: MutableStateFlow<SealedGetFamily> =
        MutableStateFlow(SealedGetFamily.Loading)
    val familyListFlow: StateFlow<SealedGetFamily> = mutableFamilyListFlow

    suspend fun getFamilyList() {
        try {
            mutableFamilyListFlow.value = SealedGetFamily.Success(apiService.getFamilyMembers())
        } catch (e: Exception) {
            mutableFamilyListFlow.value = SealedGetFamily.Error(e.message ?: "Unknown error")
        }
    }
}
