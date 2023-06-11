package ru.kapuchinka.kinosklad.zamenit_vso.films

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R

class FilmsFragment : Fragment() {
    lateinit var adapter: FilmAdapder
    lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_films, container, false)
        recyclerView = view.findViewById(R.id.r_v_films)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = FilmAdapder(getDataFilms() as MutableList<FilmModel>)
        recyclerView.adapter = adapter

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDataFilms(): List<FilmModel> {
        var year : Int = 1970
        val filmModels : MutableList<FilmModel> = java.util.ArrayList()
        filmModels.add(FilmModel("Великолепный век", "Турция", 2011))
        filmModels.add(FilmModel("Великолепный век: Империя Кёсем", "Турция", 2015))
        filmModels.add(FilmModel("Королева ночи", "Турция", 2016))
        filmModels.add(FilmModel("Постучись в мою дверь", "Турция", 2020))
        filmModels.add(FilmModel("Королёк - птичка певчая", "Турция", 2013))
        filmModels.add(FilmModel("Основание: Осман", "Турция", 2019))

        return filmModels
    }
}