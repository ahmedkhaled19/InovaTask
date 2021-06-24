package android.ahmed.khaled.inovatask.ui.main_screen.view_model

import android.ahmed.khaled.inovatask.bases.BaseViewModel
import android.ahmed.khaled.inovatask.helpers.TrainingSeriesHelper
import android.ahmed.khaled.inovatask.network.models.TrainingSeries
import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * Created by Ahmed Khaled on 24/06/2021.
 */

class MainViewModel @ViewModelInject constructor(private val trainingHelper: TrainingSeriesHelper) :
    BaseViewModel() {

    val trainingSeriesLiveData: MutableLiveData<TrainingSeries> by lazy {
        MutableLiveData<TrainingSeries>()
    }

    fun loadTrainingSeries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                trainingHelper.getTrainingSeries(getApplication<Application>().applicationContext) { trainingSeries, errorMessage ->
                    if (trainingSeries == null) {
                        showServerMessage(errorMessage!!)
                    } else {
                        trainingSeriesLiveData.postValue(trainingSeries)
                    }
                }
            } catch (throwable: Throwable) {
                if (throwable !is IOException) {
                    throw RuntimeException(throwable)
                }
                //get offline date
            }
        }
    }
}