package com.chinky.localandroidapplication.activity.student.model

data class Student(
    val unique_id: String,
    val srNo: Int,
    val name: String?,
    val gender: String?,
    val klass: String?,
    val father_name: String?,
    val mother_name: String?,
    val caste: String?,
    val category: String?,
    val institute: String?,
    val school_name: String?,
    val isPresent: Int,
    val attendance_date: String?,
    val absent_reason: String?,
    val biodiversity_Grade: String?,
    val biodiversity_Unit: Int,
    val hindi_Unit: Int,
    val maths_Grade: String?,
    val hindi_Grade: String?,
    val math_Unit: Int,
    val dob: String?
)
