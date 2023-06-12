package ru.kapuchinka.kinosklad.view.films

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.adapter.film.FilmAdapter
import ru.kapuchinka.kinosklad.viewmodel.film.FilmViewModel

class FilmsFragment : Fragment() {
    lateinit var adapter: FilmAdapter
    lateinit var recyclerView: RecyclerView

    lateinit var filmViewModel: FilmViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_films, container, false)
        filmViewModel = ViewModelProvider(this)[FilmViewModel::class.java]
        val categoryId = arguments?.getInt("categoryId", 0)

        recyclerView = view.findViewById(R.id.r_v_films)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = FilmAdapter()
        recyclerView.adapter = adapter

        if (categoryId != null) {
            filmViewModel.getFilmByCategoryId(categoryId)
        }
        filmViewModel.filmList.observe(viewLifecycleOwner) { filmList ->
            adapter.setList(filmList.films)
        }

        return view
    }
}