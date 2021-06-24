package android.ahmed.khaled.inovatask.ui.main_screen.view

import android.ahmed.khaled.inovatask.R
import android.ahmed.khaled.inovatask.bases.BaseActivity
import android.ahmed.khaled.inovatask.bases.BaseViewModel
import android.ahmed.khaled.inovatask.databinding.ActivityMainBinding
import android.ahmed.khaled.inovatask.network.models.TrainingSeries
import android.ahmed.khaled.inovatask.ui.main_screen.view_model.MainViewModel
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.trainingSeriesLiveData.observe(this, Observer {
            setDataIntoView(it!!)
        })
    }

    private fun setDataIntoView(trainingSeries: TrainingSeries) {
        Glide.with(this)
            .load(trainingSeries.coverPath)
            .into(binding.activityMainCoverPhoto)
    }

    override fun getBaseViewModel(): BaseViewModel? {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return viewModel
    }

    override fun getActivityBinding(): View {
        return binding.root
    }
}