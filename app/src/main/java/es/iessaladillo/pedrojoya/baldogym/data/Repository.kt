package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

interface Repository {


    fun queryTrainingSessionsOfDay(weekDay: WeekDay): List<TrainingSession>
    fun joinTrainingSession(trainingSessionId: Long)
    fun leftTrainingSession(trainingSessionId: Long)



}