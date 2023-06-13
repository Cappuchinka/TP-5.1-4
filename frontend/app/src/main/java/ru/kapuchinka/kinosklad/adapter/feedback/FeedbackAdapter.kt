package ru.kapuchinka.kinosklad.adapter.feedback

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.api.model.feedback.Feedback

class FeedbackAdapter() : RecyclerView.Adapter<FeedbackAdapter.FeedBackViewHolder>() {
    var feedbacks = emptyList<Feedback>()

    class FeedBackViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName : TextView = itemView.findViewById(R.id.item_name_user)
        var feedback : TextView = itemView.findViewById(R.id.item_feedback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedBackViewHolder {
        val feedbackItems : View = LayoutInflater.from(parent.context).inflate(R.layout.item_feedback_layout, parent, false)
        return FeedBackViewHolder(feedbackItems)
    }

    override fun onBindViewHolder(holder: FeedBackViewHolder, position: Int) {
        val feedback = feedbacks[position]
        holder.userName.text = feedback.nickname
        holder.feedback.text = feedback.feedback_text

        val bundle = Bundle()
        bundle.putString("userName", feedback.nickname)
        bundle.putString("userName", feedback.feedback_text)
    }

    override fun getItemCount(): Int {
        return feedbacks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(feedbackList: List<Feedback>) {
        feedbacks = feedbackList
        notifyDataSetChanged()
    }

}