package com.chinky.localandroidapplication.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chinky.localandroidapplication.R
import com.chinky.localandroidapplication.activity.family.FamilyActivity
import com.chinky.localandroidapplication.activity.student.StudentActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
    }

    private fun initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        val btnFamily = findViewById<Button>(R.id.btnFamily)
        val btnStudent = findViewById<Button>(R.id.btnStudent)
        btnStudent.setOnClickListener { v ->
            startActivity(Intent(this, StudentActivity::class.java))
        }
        btnFamily.setOnClickListener { v ->
            startActivity(Intent(this, FamilyActivity::class.java))
        }
    }
}