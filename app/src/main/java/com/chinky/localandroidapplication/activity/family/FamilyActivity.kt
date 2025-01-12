package com.chinky.localandroidapplication.activity.family

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
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
import com.chinky.localandroidapplication.activity.family.adapter.FamilyAdapter
import com.chinky.localandroidapplication.retrofit.RetrofitHelper
import com.chinky.localandroidapplication.utility.Utility
import com.chinky.localandroidapplication.activity.family.viewModel.FamilyActivityViewModel
import com.chinky.localandroidapplication.activity.family.viewModel.SealedGetFamily
import com.chinky.localandroidapplication.viewModelFactory.ViewModelFactory
import kotlinx.coroutines.launch

class FamilyActivity : AppCompatActivity() {

    private lateinit var tvNoDataFoundLabel: TextView
    private lateinit var progressBarMain: ProgressBar
    private lateinit var rvFamilyList: RecyclerView
    private lateinit var viewModel: FamilyActivityViewModel

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        tvNoDataFoundLabel = findViewById(R.id.tvNoDataFoundLabel)
        progressBarMain = findViewById(R.id.progressBarMain)
        rvFamilyList = findViewById(R.id.rvFamilyList)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(RetrofitHelper.getApiService())
        )[FamilyActivityViewModel::class.java]
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