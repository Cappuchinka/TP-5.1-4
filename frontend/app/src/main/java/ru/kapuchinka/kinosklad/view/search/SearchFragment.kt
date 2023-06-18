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
import com.yandex.metrica.YandexMetrica
import ru.kapuchinka.kinosklad.R
import ru.kapuchinka.kinosklad.adapter.search.SearchAdapter
import ru.kapuchinka.kinosklad.api.model.film.Film
import ru.kapuchinka.kinosklad.databinding.FragmentSearchBinding
import ru.kapuchinka.kinosklad.utils.YandexMetrica.YandexEvents
import ru.kapuchinka.kinosklad.viewmodel.search.SearchViewModel

class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {
    private lateinit var adapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val STATE_ADAPTER_DATA = "adapterData"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        recyclerView = binding.rVSearch
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Создаем адаптер только один раз, если он еще не был инициализирован
        if (!::adapter.isInitialized) {
            adapter = SearchAdapter(this)
        }

        recyclerView.adapter = adapter

        val searchButton: ImageButton = binding.buttonSearch

        searchButton.setOnClickListener {
            val searchText = binding.searchStroke.text.toString().trim()

            if (searchText == "." || searchText == "..") {
                Toast.makeText(requireContext(), "Недопустимые символы в поисковом запросе", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (searchText.isEmpty()) {
                Toast.makeText(requireContext(), "Введите название фильма", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            searchViewModel.getFilmsByName(searchText)
            searchViewModel.searchList.observe(viewLifecycleOwner) { films ->
                adapter.setList(films.films)
            }

            YandexMetrica.reportEvent(YandexEvents.SEARCH_FILM)
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(STATE_ADAPTER_DATA, ArrayList(adapter.searchList)) // Сохраняем данные адаптера в Bundle
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            val adapterData = savedInstanceState.getParcelableArrayList<Film>(STATE_ADAPTER_DATA)
            adapter.setList(adapterData ?: emptyList()) // Если данные нулевые, используем пустой список
        }
    }

    override fun onItemClick(film: Film) {
        val filmId = film.film_id
        val bundle = Bundle()
        bundle.putInt("filmId", filmId)
        findNavController().navigate(R.id.action_navigation_search_to_filmPageFragment, bundle)
    }
}

