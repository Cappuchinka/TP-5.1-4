package ru.kapuchinka.kinosklad.view.filmpage

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.adapter.feedback.FeedbackAdapter
import ru.kapuchinka.kinosklad.databinding.FragmentFilmPageBinding
import ru.kapuchinka.kinosklad.utils.db.DBManager
import ru.kapuchinka.kinosklad.viewmodel.feedback.FeedbackViewModel
import ru.kapuchinka.kinosklad.viewmodel.filmpage.FilmPageViewModel

class FilmPageFragment : Fragment() {
    private lateinit var pref: SharedPreferences
    lateinit var adapter: FeedbackAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentFilmPageBinding
    private val filmPageViewModel: FilmPageViewModel by viewModels()
    private val feedbackViewModel: FeedbackViewModel by viewModels()
    private lateinit var dbManager: DBManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pref = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmPageBinding.inflate(inflater, container, false)
        dbManager = context?.let { DBManager(it) }!!

        val filmId = arguments?.getInt("filmId", 0)
        recyclerView = binding.rVFeedbacks
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = FeedbackAdapter()
        recyclerView.adapter = adapter

        if (filmId != null) {
            filmPageViewModel.getFilmById(filmId)
        }
        filmPageViewModel.film.observe(viewLifecycleOwner) {
            binding.pageFilmName.text = filmPageViewModel.film.value!!.name
            val country = filmPageViewModel.film.value!!.country
            val year = filmPageViewModel.film.value!!.releaseDate
            binding.pageFilmCountryYear.text = "$country ($year)"
            binding.pageFilmDescription.text = filmPageViewModel.film.value!!.description
        }
        if (filmId != null) {
            feedbackViewModel.getFeedbackByFilmId(filmId)
        }
        feedbackViewModel.feedbacks.observe(viewLifecycleOwner) {
            adapter.setList(it.feedbacks)
        }
        val feedbackButton : Button = binding.buttonFeedback

        feedbackButton.setOnClickListener {
            val token = getToken()
            if  (token == "") {
                Toast.makeText(requireContext(), "Авторизируйтесь", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val bundle = Bundle()
            bundle.putInt("filmId", filmId!!)
            bundle.putString("token", token)

            it.findNavController().navigate(R.id.action_filmPageFragment_to_addFeedBackFragment, bundle)
        }

        val addFavoriteFilm : ImageButton = binding.buttonAddFavorite
        addFavoriteFilm.setOnClickListener {
            dbManager.openDB()
            dbManager.insertToDB(filmPageViewModel.film.value!!.film_id,
                filmPageViewModel.film.value!!.name,
                filmPageViewModel.film.value!!.country,
                filmPageViewModel.film.value!!.releaseDate)
            Toast.makeText(requireContext(), "Фильм добавлен в избранное", Toast.LENGTH_SHORT).show()
            addFavoriteFilm.setImageResource(R.drawable.bookmark_added)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDB()
    }

    private fun getToken() : String {
        return pref.getString("token", "")!!
    }
}