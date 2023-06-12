package ru.kapuchinka.kinosklad.view.favorite

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
import ru.kapuchinka.kinosklad.adapter.favorite.FavoriteAdapter
import ru.kapuchinka.kinosklad.api.model.favorite.FavoriteFilm
import ru.kapuchinka.kinosklad.viewmodel.favorite.FavoriteFilmViewModel

class FavoriteFragment : Fragment(), FavoriteAdapter.OnItemClickListener {
    lateinit var adapter: FavoriteAdapter
    lateinit var recyclerView: RecyclerView

    private val favoriteFilmViewModel: FavoriteFilmViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        recyclerView = view.findViewById(R.id.r_v_favorites)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = FavoriteAdapter(this)
        recyclerView.adapter = adapter

        favoriteFilmViewModel.getFavoriteFilms(requireContext())
        favoriteFilmViewModel.favoriteFilms.observe(viewLifecycleOwner) {
            adapter.setList(it.favoriteFilms)
        }

        return view
    }

    override fun onItemClick(favoriteFilm: FavoriteFilm) {
        val filmId = favoriteFilm.filmId
        val bundle = Bundle()
        bundle.putInt("filmId", filmId)
        findNavController().navigate(R.id.action_navigation_favorites_to_filmPageFragment, bundle)
    }


}