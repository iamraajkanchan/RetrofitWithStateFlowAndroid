package com.chinky.localandroidapplication.activity.student.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.chinky.localandroidapplication.activity.student.model.Student
import com.chinky.localandroidapplication.databinding.StudentAdapterRowItemBinding

class StudentAdapter :
    PagingDataAdapter<Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding =
            StudentAdapterRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)
        if (student != null) {
            holder.bind(student)
        }
    }

    class StudentViewHolder(private val binding: StudentAdapterRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.studentName.text = student.name
            binding.studentClass.text = student.klass
            binding.studentGender.text = student.gender
            binding.studentDOB.text = student.dob
            binding.studentSchoolName.text = student.school_name
        }
    }

    class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.unique_id == newItem.unique_id
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }
    }

}
