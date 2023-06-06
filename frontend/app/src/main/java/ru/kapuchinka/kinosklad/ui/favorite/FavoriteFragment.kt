package ru.kapuchinka.kinosklad.ui.favorite

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

class FavoriteFragment : Fragment() {
    lateinit var adapter: FavoriteAdapter
    lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        recyclerView = view.findViewById(R.id.r_v_favorites)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = FavoriteAdapter(getDataFilms() as MutableList<FavoriteModel>)
        recyclerView.adapter = adapter

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDataFilms(): List<FavoriteModel> {
        val favoriteModels : MutableList<FavoriteModel> = java.util.ArrayList()
        favoriteModels.add(FavoriteModel("Тьма", "Германия", 2017))
        favoriteModels.add(FavoriteModel("1899", "Германия", 2022))
        favoriteModels.add(FavoriteModel("Бумажный дом", "Италия", 2017))

        return favoriteModels
    }


}