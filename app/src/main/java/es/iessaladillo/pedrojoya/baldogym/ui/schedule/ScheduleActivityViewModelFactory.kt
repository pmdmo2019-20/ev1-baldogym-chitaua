package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.baldogym.data.Repository

class ScheduleActivityViewModelFactory(
    private val repository: Repository
): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ScheduleActivityViewModel(repository) as T


}