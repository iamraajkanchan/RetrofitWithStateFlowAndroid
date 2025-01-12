package com.chinky.localandroidapplication.activity.student.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chinky.localandroidapplication.activity.student.model.Student
import com.chinky.localandroidapplication.repository.ApiService
import com.chinky.localandroidapplication.utility.Utility

class StudentPagingSource(private val apiService: ApiService) : PagingSource<Int, Student>() {

    override fun getRefreshKey(state: PagingState<Int, Student>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Student> {
        return try {
            val page = params.key ?: 0
            Utility.printLog(
                StudentPagingSource::class.java,
                if (Thread.currentThread().stackTrace.size > 2)
                    Thread.currentThread().stackTrace.get(2)
                else null,
                "page : $page"
            )
            Utility.printLog(
                StudentPagingSource::class.java,
                if (Thread.currentThread().stackTrace.size > 2)
                    Thread.currentThread().stackTrace.get(2)
                else null,
                "params.key : ${params.key}"
            )
            val response = apiService.getStudents(page, params.loadSize)
            Utility.printLog(
                StudentPagingSource::class.java,
                if (Thread.currentThread().stackTrace.size > 2)
                    Thread.currentThread().stackTrace.get(2)
                else null,
                "response : $response"
            )
            LoadResult.Page(
                data = response.content,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.last) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}