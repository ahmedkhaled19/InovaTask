package android.ahmed.khaled.inovatask.di

import android.ahmed.khaled.inovatask.helpers.TrainingSeriesHelper
import android.ahmed.khaled.inovatask.network.deserializer.TrainingSeriesResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object HelpersModule {

    @Provides
    fun provideTrainingSeriesResponse(): TrainingSeriesResponse {
        return TrainingSeriesResponse()
    }

    @Provides
    fun provideMoviesDataHelper(trainingSeriesResponse: TrainingSeriesResponse): TrainingSeriesHelper {
        return TrainingSeriesHelper(trainingSeriesResponse)
    }

}