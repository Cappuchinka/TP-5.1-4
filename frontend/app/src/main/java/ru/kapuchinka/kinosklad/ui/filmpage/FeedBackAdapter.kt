package ru.kapuchinka.kinosklad.ui.filmpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R

class FeedBackAdapter(_feedbackList : MutableList<FeedBackModel>) : RecyclerView.Adapter<FeedBackAdapter.FeedBackViewHolder>() {
    private val feedbackList : MutableList<FeedBackModel> = _feedbackList

    class FeedBackViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName : TextView = itemView.findViewById(R.id.item_name_user)
        var feedback : TextView = itemView.findViewById(R.id.item_feedback)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedBackViewHolder {
        val feedbackItems : View = LayoutInflater.from(parent.context).inflate(R.layout.item_feedback_layout, parent, false)
        return FeedBackAdapter.FeedBackViewHolder(feedbackItems)
    }

    override fun onBindViewHolder(holder: FeedBackViewHolder, position: Int) {
        holder.userName.text = feedbackList[position].userName
        holder.feedback.text = feedbackList[position].feedback

        val bundle = Bundle()
        bundle.putString("userName", feedbackList[position].userName)
        bundle.putString("userName", feedbackList[position].feedback)
    }

    override fun getItemCount(): Int {
        return feedbackList.size
    }

}