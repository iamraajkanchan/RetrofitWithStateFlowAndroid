package com.chinky.localandroidapplication.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.chinky.localandroidapplication.R
import com.chinky.localandroidapplication.adpater.FamilyAdapter
import com.chinky.localandroidapplication.retrofit.RetrofitHelper
import com.chinky.localandroidapplication.utility.Utility
import com.chinky.localandroidapplication.viewModel.MainActivityViewModel
import com.chinky.localandroidapplication.viewModel.SealedGetFamily
import com.chinky.localandroidapplication.viewModel.ViewModelFactory
import kotlinx.coroutines.launch

class FamilyActivity : AppCompatActivity() {

    private lateinit var tvNoDataFoundLabel: TextView
    private lateinit var progressBarMain: ProgressBar
    private lateinit var rvFamilyList: RecyclerView
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_family)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
    }

    private fun initViews() {
        tvNoDataFoundLabel = findViewById(R.id.tvNoDataFoundLabel)
        progressBarMain = findViewById(R.id.progressBarMain)
        rvFamilyList = findViewById(R.id.rvFamilyList)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RetrofitHelper.getApiService())
        )[MainActivityViewModel::class.java]
        viewModel.let { vm ->
            lifecycleScope.launch {
                vm.getFamilyList()
                vm.familyListFlow.collect { state ->
                    when (state) {
                        is SealedGetFamily.Error -> {
                            setProgressBarVisibility(ProgressBar.GONE)
                            Utility.printLog(
                                FamilyActivity::class.java,
                                if (Thread.currentThread().stackTrace.size > 2) Thread.currentThread().stackTrace[2] else null,
                                "Error Message :: ${state.message}"
                            )
                            showToast(state.message)
                            tvNoDataFoundLabel.visibility = View.VISIBLE
                            rvFamilyList.visibility = View.GONE
                        }

                        is SealedGetFamily.Success -> {
                            setProgressBarVisibility(ProgressBar.GONE)
                            rvFamilyList.adapter =
                                FamilyAdapter(state.familyList, this@FamilyActivity)
                            rvFamilyList.visibility = View.VISIBLE
                            tvNoDataFoundLabel.visibility = View.GONE
                        }

                        is SealedGetFamily.Loading -> setProgressBarVisibility(View.VISIBLE)
                    }
                }
            }
        }
    }

    private fun setProgressBarVisibility(visibility: Int) {
        progressBarMain.visibility = visibility
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}