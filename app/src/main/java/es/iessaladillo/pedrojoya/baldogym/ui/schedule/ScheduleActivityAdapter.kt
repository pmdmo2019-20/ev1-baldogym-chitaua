package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.schedule_activity_item.*
import kotlinx.android.synthetic.main.schedule_activity_item.view.*

class ScheduleActivityAdapter: RecyclerView.Adapter<ScheduleActivityAdapter.ViewHolder>() {

    private var data: List<TrainingSession> = emptyList()
    var onItemClickListener: ((Int) -> Unit)? = null
    var onItemButtonListener: ((Int) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trainingSession: TrainingSession = data[position]
        holder.bind(trainingSession)
    }

    override fun getItemId(position: Int): Long = data[position].id

    fun getItem(position: Int): TrainingSession = data[position]


    fun submitList(trainingSessions: List<TrainingSession>) {
        data = trainingSessions
        notifyDataSetChanged()
    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }
            btnJoinedSch.setOnClickListener { onItemButtonListener?.invoke(adapterPosition) }
        }

        fun bind(trainingSession: TrainingSession) {
            trainingSession.run {
                containerView.lblTimeSch.text = time
                containerView.imgSch.setImageResource(photoResId)
                containerView.lblNameSch.text = name
                containerView.lblTrainerSch.text = trainer
                containerView.lblRoomSch.text = room

                if (participants == 1)
                    containerView.lblParticipantsSch.text = "%d participant".format(participants)
                else
                    containerView.lblParticipantsSch.text = "%d participants".format(participants)

                if (userJoined)
                    containerView.btnJoinedSch.setText(R.string.schedule_item_quit)
                else
                    containerView.btnJoinedSch.setText(R.string.schedule_item_join)
            }
        }
    }
}