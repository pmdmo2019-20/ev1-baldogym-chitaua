package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay

class ScheduleActivityViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _trainingSessions: MutableLiveData<List<TrainingSession>> = MutableLiveData()
    val trainingSessions: LiveData<List<TrainingSession>>
        get() = _trainingSessions

    private val _currentFilter: MutableLiveData<WeekDay> = MutableLiveData(getCurrentWeekDay())
    val currentFilter: LiveData<WeekDay>
        get() = _currentFilter


    fun filter(weekDay: WeekDay) {
        _currentFilter.value= weekDay
        _trainingSessions.value = repository.queryTrainingSessionsOfDay(currentFilter.value!!)
    }

    fun updateTrainingSessionUserJoinedState(trainingSession: TrainingSession, userJoin: Boolean) {
        if(userJoin){
            repository.joinTrainingSession(trainingSession.id)
        }
        else{
            repository.leftTrainingSession(trainingSession.id)
        }
        _trainingSessions.value = repository.queryTrainingSessionsOfDay(_currentFilter.value!!)
    }

}