package com.chinky.localandroidapplication.activity.student

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.chinky.localandroidapplication.R
import com.chinky.localandroidapplication.activity.student.adapter.LoadingStateAdapter
import com.chinky.localandroidapplication.activity.student.adapter.StudentAdapter
import com.chinky.localandroidapplication.activity.student.viewModel.StudentViewModel
import kotlinx.coroutines.launch

class StudentActivity : AppCompatActivity() {

    private val rvStudentList: RecyclerView by lazy { findViewById(R.id.rvStudentList) }
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
        val adapter = StudentAdapter().apply {
            withLoadStateFooter(footer = LoadingStateAdapter { retry() })
        }
        rvStudentList.adapter = adapter
        lifecycleScope.launch {
            viewModel.students.collect {
                adapter.submitData(lifecycle, it)
            }
        }
    }
}