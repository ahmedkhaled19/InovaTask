package android.ahmed.khaled.inovatask.network.models

/**
 * Created by Ahmed Khaled on 24/06/2021.
 */

data class TrainingSeries(var id: Long){

    var name: String = ""
    var coverPath: String = ""
    var overview: String = ""
    var coaches : Collection<Coaches> = mutableSetOf()
    var classes : Collection<Classes> = mutableSetOf()
    var community : Collection<Community> = mutableSetOf()

}
