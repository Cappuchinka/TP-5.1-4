package ru.kapuchinka.kinosklad.zamenit_vso.filmpage

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R

class FilmPageFragment : Fragment() {
    lateinit var adapter: FeedBackAdapter
    lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film_page, container, false)
        recyclerView = view.findViewById(R.id.r_v_feedbacks)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = FeedBackAdapter(getFeedBacks() as MutableList<FeedBackModel>)
        recyclerView.adapter = adapter

        val feedbackButton : Button = view.findViewById(R.id.button_feedback)

        feedbackButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_filmPageFragment_to_addFeedBackFragment)
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFeedBacks(): List<FeedBackModel> {
        val feedBackModels : MutableList<FeedBackModel> = java.util.ArrayList()
        feedBackModels.add(FeedBackModel("Мустафа", "Мерьем Узерли хороша в своей роли!"))
        feedBackModels.add(FeedBackModel("Хатидже", "Ибрагиииииииим!"))
        feedBackModels.add(FeedBackModel("Сюмбюль Ага", "Бисмилях-ир Рахманим Рахим! Мама миа! Что это такое?"))
        return feedBackModels
    }
}