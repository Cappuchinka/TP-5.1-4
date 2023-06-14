package ru.kapuchinka.kinosklad.view.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.adapter.search.SearchAdapter
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.databinding.FragmentSearchBinding
import ru.kapuchinka.kinosklad.viewmodel.search.SearchViewModel

class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {
    lateinit var adapter: SearchAdapter
    lateinit var recyclerView: RecyclerView

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var binding: FragmentSearchBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        recyclerView = binding.rVSearch
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = SearchAdapter(this)
        recyclerView.adapter = adapter

        val searchButton: ImageButton = binding.buttonSearch
        lateinit var searchText: String

        searchButton.setOnClickListener {
            searchText = binding.searchStroke.text.toString().trim()

            if (searchText == "") {
                Toast.makeText(requireContext(), "Введите название фильма", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            searchViewModel.getFilmsByName(searchText)
            searchViewModel.searchList.observe(viewLifecycleOwner) {
                adapter.setList(it.films)
            }
        }

        return binding.root
    }

    override fun onItemClick(film: Film) {
        val filmId = film.film_id
        val bundle = Bundle()
        bundle.putInt("filmId", filmId)
        findNavController().navigate(R.id.action_navigation_search_to_filmPageFragment, bundle)
    }
}