package com.chinky.localandroidapplication.activity.family.viewModel

import com.chinky.localandroidapplication.activity.family.model.FamilyMember

sealed class SealedGetFamily {
    data class Success(val familyList: List<FamilyMember>) : SealedGetFamily()
    data class Error(val message: String) : SealedGetFamily()
    data object Loading : SealedGetFamily()
}