package ru.kapuchinka.kinosklad.ui.films

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
        filmModels.add(FilmModel("Film_1", "USSR", year++))
        filmModels.add(FilmModel("Film_2", "Russia", year++))
        filmModels.add(FilmModel("Film_3", "Germany", year++))
        filmModels.add(FilmModel("Film_4", "USA", year++))
        filmModels.add(FilmModel("Film_5", "Italy", year++))
        filmModels.add(FilmModel("Film_6", "France", year++))

        return filmModels
    }


}