package com.chinky.localandroidapplication.activity.student.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chinky.localandroidapplication.activity.student.model.Student
import com.chinky.localandroidapplication.activity.student.source.StudentPagingSource
import com.chinky.localandroidapplication.retrofit.RetrofitHelper
import kotlinx.coroutines.flow.Flow

class StudentViewModel : ViewModel() {
    private val apiService = RetrofitHelper.getApiService()
    val students: Flow<PagingData<Student>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { StudentPagingSource(apiService) }).flow.cachedIn(viewModelScope)
}