package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

interface Repository {

    fun queryMondayTrainingSessions(): List<TrainingSession>
    fun queryTuesdayTrainingSessions(): List<TrainingSession>
    fun queryWednesdayTrainingSessions(): List<TrainingSession>
    fun queryThursdayTrainingSessions(): List<TrainingSession>
    fun queryFridayTrainingSessions(): List<TrainingSession>
    fun querySaturdayTrainingSessions(): List<TrainingSession>
    fun querySundayTrainingSessions(): List<TrainingSession>
    fun joinTrainingSession(trainingSessionId: Long)
    fun leftTrainingSession(trainingSessionId: Long)



}