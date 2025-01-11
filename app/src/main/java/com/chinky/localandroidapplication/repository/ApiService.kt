package com.chinky.localandroidapplication.repository

import com.chinky.localandroidapplication.activity.family.model.FamilyMember
import com.chinky.localandroidapplication.activity.student.model.StudentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("families")
    suspend fun getFamilyMembers(): List<FamilyMember>

    @GET("studentsToAndFrom")
    suspend fun getStudents(@Query("page") page: Int, @Query("size") size: Int): StudentResponse
}