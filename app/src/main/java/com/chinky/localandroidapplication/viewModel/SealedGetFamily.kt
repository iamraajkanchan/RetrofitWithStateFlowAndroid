package com.chinky.localandroidapplication.viewModel

import com.chinky.localandroidapplication.model.FamilyMember

sealed class SealedGetFamily {
    data class Success(val familyList: List<FamilyMember>) : SealedGetFamily()
    data class Error(val message: String) : SealedGetFamily()
    data object Loading : SealedGetFamily()
}