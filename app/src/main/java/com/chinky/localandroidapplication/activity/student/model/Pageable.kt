package com.chinky.localandroidapplication.activity.student.model

data class Pageable(
    val pageNumber: Int,
    val pageSize: Int,
    val offset: Long,
    val paged: Boolean,
    val unpaged: Boolean
)
