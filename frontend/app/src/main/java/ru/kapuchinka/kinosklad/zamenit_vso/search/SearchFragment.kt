package ru.kapuchinka.kinosklad.zamenit_vso.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.zamenit_vso.films.FilmModel

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
        filmModels.add(FilmModel("Человек-паук", "США", 2002))
        filmModels.add(FilmModel("Человек-паук 2", "США", 2004))
        filmModels.add(FilmModel("Человек-паук 3: Враг в отражении", "США", 2007))
        filmModels.add(FilmModel("Новый Человек-паук", "США", 2012))
        filmModels.add(FilmModel("Новый Человек-паук: Высокое напряжение", "США", 2014))

        return filmModels
    }
}