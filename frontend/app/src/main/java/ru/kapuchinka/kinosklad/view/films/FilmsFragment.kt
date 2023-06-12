package ru.kapuchinka.kinosklad.view.films

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.adapter.film.FilmAdapter
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.viewmodel.film.FilmViewModel

class FilmsFragment : Fragment(), FilmAdapter.OnItemClickListener {
    lateinit var adapter: FilmAdapter
    lateinit var recyclerView: RecyclerView

    private val filmViewModel: FilmViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_films, container, false)
        val categoryId = arguments?.getInt("categoryId", 0)
        recyclerView = view.findViewById(R.id.r_v_films)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = FilmAdapter(this)
        recyclerView.adapter = adapter

        if (categoryId != null) {
            filmViewModel.getFilmByCategoryId(categoryId)
        }
        filmViewModel.filmList.observe(viewLifecycleOwner) { filmList ->
            adapter.setList(filmList.films)
        }

        return view
    }

    override fun onItemClick(film: Film) {
        val filmId = film.film_id
        val bundle = Bundle()
        bundle.putInt("filmId", filmId)
        findNavController().navigate(R.id.action_filmsFragment_to_filmPageFragment, bundle)
    }
}