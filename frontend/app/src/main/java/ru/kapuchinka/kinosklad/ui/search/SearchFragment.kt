package ru.kapuchinka.kinosklad.ui.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.databinding.FragmentSearchBinding
import ru.kapuchinka.kinosklad.ui.films.FilmAdapder
import ru.kapuchinka.kinosklad.ui.films.FilmModel

class SearchFragment : Fragment() {
    lateinit var adapter: SearchAdapter
    lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.r_v_search)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = SearchAdapter(getDataFilms() as MutableList<FilmModel>)
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