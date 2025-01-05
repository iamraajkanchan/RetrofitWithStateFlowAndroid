package com.chinky.localandroidapplication.repository

import com.chinky.localandroidapplication.model.FamilyMember
import retrofit2.http.GET

interface ApiService {
    @GET("families")
    suspend fun getFamilyMembers(): List<FamilyMember>
}