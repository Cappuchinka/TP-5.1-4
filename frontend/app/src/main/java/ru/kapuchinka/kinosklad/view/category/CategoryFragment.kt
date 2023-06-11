package ru.kapuchinka.kinosklad.view.category

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
import ru.kapuchinka.kinosklad.databinding.FragmentHomeBinding
import ru.kapuchinka.kinosklad.adapter.CategoryAdapter
import ru.kapuchinka.kinosklad.viewmodel.CategoryViewModel

class CategoryFragment : Fragment() {
    lateinit var adapter: CategoryAdapter
    lateinit var recyclerView: RecyclerView

    lateinit var categoryViewModel: CategoryViewModel

    private lateinit var binding: FragmentHomeBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        recyclerView = view.findViewById(R.id.r_v_categories)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = CategoryAdapter()
        recyclerView.adapter = adapter

        categoryViewModel.getCategoryList()
        categoryViewModel.categoryList.observe(viewLifecycleOwner) { catList ->
            adapter.setList(catList.categories)
        }

        return view
    }

}