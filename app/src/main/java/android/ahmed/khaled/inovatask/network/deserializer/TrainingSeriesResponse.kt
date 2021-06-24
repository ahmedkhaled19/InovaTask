package android.ahmed.khaled.inovatask.network.deserializer

import android.ahmed.khaled.inovatask.network.models.Classes
import android.ahmed.khaled.inovatask.network.models.Coaches
import android.ahmed.khaled.inovatask.network.models.Community
import android.ahmed.khaled.inovatask.network.models.TrainingSeries
import android.content.Context
import org.json.JSONObject
import java.io.IOException

/**
 * Created by Ahmed Khaled on 24/06/2021.
 */

class TrainingSeriesResponse {

    suspend fun getTrainingSeries(context: Context) : TrainingSeries?{
        val jsonString: String
        try {
            jsonString = context.assets.open("json_response").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return deserializeTrainingResponse(jsonString)
    }

    private fun deserializeTrainingResponse(jsonString: String): TrainingSeries {
        val trainingSeriesObject = JSONObject(jsonString)
        val trainingSeries = TrainingSeries(trainingSeriesObject.getLong("id"))
        trainingSeries.name = trainingSeriesObject.getString("name")
        trainingSeries.coverPath = trainingSeriesObject.getString("cover_url")
        trainingSeries.overview = trainingSeriesObject.getString("overview")

        val coachesObject = trainingSeriesObject.optJSONArray("coaches")
        coachesObject?.let {
            val coachesList = mutableSetOf<Coaches>()
            for (i in 0 until coachesObject.length()) {
                val coachObject = coachesObject.getJSONObject(i)
                val coach = Coaches(coachObject.getString("name"))
                coach.overview = coachObject.getString("overview")
                coachesList.add(coach)
            }
            trainingSeries.coaches = coachesList
        }

        val classesObject = trainingSeriesObject.optJSONArray("classes")
        classesObject?.let {
            val classesList = mutableSetOf<Classes>()
            for (i in 0 until classesObject.length()) {
                val classObject = classesObject.getJSONObject(i)
                val classes = Classes(classObject.getString("name"))
                classes.duration = classObject.getString("duration")
                classes.videoLink = classObject.getString("video_link")
                classesList.add(classes)
            }
            trainingSeries.classes = classesList
        }

        val communitiesObject = trainingSeriesObject.optJSONArray("community")
        communitiesObject?.let {
            val communityList = mutableSetOf<Community>()
            for (i in 0 until communitiesObject.length()) {
                val communityObject = communitiesObject.getJSONObject(i)
                val community = Community(communityObject.getString("writer"))
                community.post = communityObject.getString("post")
                communityList.add(community)
            }
            trainingSeries.community = communityList
        }

        return trainingSeries
    }
}