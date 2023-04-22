package ru.kapuchinka.kinosklad.ui.home

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

class HomeFragment : Fragment() {
    lateinit var adapter: CategoryAdapter
    lateinit var recyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.r_v_categories)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = CategoryAdapter(getDataCategories() as MutableList<CategoryModel>)
        recyclerView.adapter = adapter

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDataCategories(): List<CategoryModel> {
        val categoryModels: MutableList<CategoryModel> = java.util.ArrayList()

        categoryModels.add(CategoryModel("Ужасы"))
        categoryModels.add(CategoryModel("Триллеры"))
        categoryModels.add(CategoryModel("Драмы"))
        categoryModels.add(CategoryModel("Приключения"))
        categoryModels.add(CategoryModel("Фантастика"))
        categoryModels.add(CategoryModel("Комедии"))
        categoryModels.add(CategoryModel("Боевики"))
        categoryModels.add(CategoryModel("Сказки"))
        categoryModels.add(CategoryModel("Мультфильмы"))

        return categoryModels
    }

}