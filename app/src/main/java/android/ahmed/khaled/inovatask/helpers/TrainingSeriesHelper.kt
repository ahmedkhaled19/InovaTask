package android.ahmed.khaled.inovatask.helpers

import android.ahmed.khaled.inovatask.R
import android.ahmed.khaled.inovatask.network.deserializer.TrainingSeriesResponse
import android.ahmed.khaled.inovatask.network.models.TrainingSeries
import android.content.Context
import javax.inject.Inject

/**
 * Created by Ahmed Khaled on 24/06/2021.
 */

class TrainingSeriesHelper @Inject constructor(private val trainingSeriesResponse: TrainingSeriesResponse) {


    suspend fun getTrainingSeries(context: Context,resultsCallback: (TrainingSeries?, String?) -> Unit){
        val response = trainingSeriesResponse.getTrainingSeries(context)
        if (response != null) {
            resultsCallback(null, "No data found")
        } else {
            resultsCallback(response, null)
        }
    }
}