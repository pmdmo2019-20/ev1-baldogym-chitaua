package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleActivityViewModel by viewModels {
        ScheduleActivityViewModelFactory(LocalRepository)
    }

    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().also {


        it.onItemButtonListener = {position ->
            changeUserJoin(position)
        }
    }

    private fun changeUserJoin(position: Int) {
        val trainingSession: TrainingSession = listAdapter.getItem(position)
        viewModel.updateTrainingSessionUserJoinedState(trainingSession, trainingSession.userJoined)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupViews()
        observeViewModelData()
    }

    private fun setupViews() {
        changeWeekBar(viewModel.currentFilter.value!!)
        setupWeekBar()
        setupDayBar()
        setupRecyclerView()
    }

    private fun setupWeekBar() {
        lblMonSch.setOnClickListener { changeWeekBar(WeekDay.MONDAY) }
        lblTueSch.setOnClickListener { changeWeekBar(WeekDay.TUESDAY) }
        lblWedSch.setOnClickListener { changeWeekBar(WeekDay.WEDNESDAY) }
        lblThuSch.setOnClickListener { changeWeekBar(WeekDay.THURSDAY) }
        lblFriSch.setOnClickListener { changeWeekBar(WeekDay.FRIDAY) }
        lblSatSch.setOnClickListener { changeWeekBar(WeekDay.SATURDAY) }
        lblSunSch.setOnClickListener { changeWeekBar(WeekDay.SUNDAY) }
    }

    private fun changeWeekBar(weekDay: WeekDay) {
        lblMonSch.setTextColor(resources.getColor(R.color.white_semi))
        lblTueSch.setTextColor(resources.getColor(R.color.white_semi))
        lblWedSch.setTextColor(resources.getColor(R.color.white_semi))
        lblThuSch.setTextColor(resources.getColor(R.color.white_semi))
        lblFriSch.setTextColor(resources.getColor(R.color.white_semi))
        lblSatSch.setTextColor(resources.getColor(R.color.white_semi))
        lblSunSch.setTextColor(resources.getColor(R.color.white_semi))
        when(weekDay) {
            WeekDay.MONDAY -> lblMonSch.setTextColor(resources.getColor(R.color.white))
            WeekDay.TUESDAY -> lblTueSch.setTextColor(resources.getColor(R.color.white))
            WeekDay.WEDNESDAY -> lblWedSch.setTextColor(resources.getColor(R.color.white))
            WeekDay.THURSDAY -> lblThuSch.setTextColor(resources.getColor(R.color.white))
            WeekDay.FRIDAY -> lblFriSch.setTextColor(resources.getColor(R.color.white))
            WeekDay.SATURDAY -> lblSatSch.setTextColor(resources.getColor(R.color.white))
            WeekDay.SUNDAY -> lblSunSch.setTextColor(resources.getColor(R.color.white))
        }
        viewModel.filter(weekDay)
    }

    private fun setupDayBar() {
        lblCurrDaySch.text = getCurrentWeekDay().name
    }

    private fun setupRecyclerView() {
        lsTrainingSessions.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun observeViewModelData() {
        observeTrainingSessions()
    }

    private fun observeTrainingSessions() {
        viewModel.trainingSessions.observe(this) {
            showTrainingSessions(it)
        }
    }

    private fun showTrainingSessions(trainingSession: List<TrainingSession>) {
        lsTrainingSessions.post {
            listAdapter.submitList(trainingSession)
        }
    }
}
