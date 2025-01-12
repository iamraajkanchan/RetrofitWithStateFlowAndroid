package com.chinky.localandroidapplication.activity.student

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chinky.localandroidapplication.R
import com.chinky.localandroidapplication.activity.student.adapter.LoadingStateAdapter
import com.chinky.localandroidapplication.activity.student.adapter.StudentAdapter
import com.chinky.localandroidapplication.activity.student.viewModel.StudentViewModel
import kotlinx.coroutines.launch

class StudentActivity : AppCompatActivity() {

    private val rvStudentList: RecyclerView by lazy { findViewById(R.id.rvStudentList) }
    private val progressBarStudent: ProgressBar by lazy { findViewById(R.id.progressBarStudent) }
    private val viewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
    }

    private fun initViews() {

        val studentAdapter = StudentAdapter()
        val loadingStateAdapter = LoadingStateAdapter { studentAdapter.retry() }
        val concatAdapter = ConcatAdapter(studentAdapter, loadingStateAdapter)
        rvStudentList.adapter = concatAdapter
        lifecycleScope.launch {
            viewModel.students.collect {
                studentAdapter.submitData(lifecycle, it)
            }
        }
        studentAdapter.addLoadStateListener {
            loadingStateAdapter.loadState = it.append
            progressBarStudent.visibility = when (it.append) {
                is LoadState.Loading -> View.VISIBLE
                else -> View.GONE
            }
        }
    }
}